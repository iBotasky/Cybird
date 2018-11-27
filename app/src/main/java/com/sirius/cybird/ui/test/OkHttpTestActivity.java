package com.sirius.cybird.ui.test;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.sirius.cybird.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp源码解析
 */
public class OkHttpTestActivity extends AppCompatActivity {
    public static final String TAG = "OkHttp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    //region OkHttp同步请求
    private void syncRequest() {
        /**
         * 在当前线程发送同步请求后，当前线程会进入阻塞状态，直到收到响应
         */

        //1.Create OkHttpClient 通过OkHttp内部类Builder设置请求参数，再通过build方法生成Client
        //Builder内部有一个dispatcher类，是用来做同步，异步请求的分发，分发器类，OkHttp的核心类之一
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        //2.Create Request 同样是通过Builder设置参数和请求方法调用build生成Request
        Request syncRequest = new Request.Builder().url("http://www.baidu.com").get().build();
        //3.Create Call 链接Response和Request的桥梁，一个Call对应一次请求
        //call 是一个接口，实际上在在Client.newCall的时候返回一个RealCall，RealCall集成了Call接口
        //RealCall在创建的时候赋值了Client，request，创建了重定向拦截器，及一个事件监听
        Call call = client.newCall(syncRequest);
        //4.发起同步请求获取Response
        try {
            //excute同样是在RealCall中去实现
            //在同步代码块中判断时候已经被执行，执行过就抛异常，未执行就标记为true
            //在RealCall中client.dispatcher.exechte(call)中，Client的dispatcher会吧call加入到同步请求队列（runningSyncCalls）中
            //执行完execute，会通过调用getResponseWithInterceptorChain()获取返回response
            //finally client.dispatcher.finish去移除当前runningSyncCalls中的当前的同步call
            Response response = call.execute();
            Log.e(TAG, "Response " + response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion



    //region OkHttp异步请求
    private void aSyncRequest(){
        //与同步相同前三步都是创建client，call，request
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url("http://www.baidu.com").get().build();
        Call  call = client.newCall(request);

        //发起异步请求, 调用enqueue会开启一个新的工作线程, callback的onFailure 和 onResponse都是在工作线程去执行，所以这边不能做一些View的更新操作
        //同样是调用RealCall的实现方法RealCall.enqueue,
        //传入的callback对象会被封装正一个 AsyncCall的Runnable
        //realcall里面又调用了client.dispatcher.enqueue，传入callback
        //如果当前正在执行的异步请求runningAsyncCalls的数量小于最大请求数&runningCallHost小于最大请求Host就把当前call加入到runningAsyncCalls， 通过线程池ExecutorService.execute去执行call
        //否则加入到readAsyncCalls队列中
        //executorService线程池中执行的execute(call)则是调用的AsyncCall的execute
        //AsyncCall.execute先通过拦截器链获取Response，如果重定向重试拦截器取消，就返回onFailure，否则返回onResponse
        //execute的finally调用了client.dispatcher.finish, remove当前call，
        call.enqueue(new Callback() {
            //异步请求失败或者调用call.cancel()的回调
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Request Fail");
            }

            //异步请求成功之后的回调
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "Response " + response.body().toString());
            }
        });

    }
    //endregion

    //region okhttp任务调度dispatcher
    private void dispatcher(){
        //Q1.okhttp如何实现管理同步异步请求
        //发送的同步/异步请求都会在dispatcher中管理状态

        //Q2.dispatcher是什么
        //dispatcher用于维护请求状态，并且内部维护一个线程池ExecuteService线程池，用于执行请求，相对于
        //其他框架okhttp高效就在于dispatcher维护的异步请求的线程池。
        //就绪请求队列 readyAsyncCalls  正在请求队列（包括未完成但被cancel的请求） runningAsyncCalls

        //Q3. 异步请求为什么需要两个队列
        //dispatcher相当于生产者，对进入的call进行调度
        //executeService线程池相当于消费者，消费者的消费个数有上限，所以需要两个队列存放call
        //当不满足当前消费状态就把call放到readyAsyncCalls队列中
        //任务执行完dispatcher会调用promoteCall()手动清楚缓存区

        //同步异步在finish的不同处在于client.dispatcher.finish有一个参数promoteCalls用来调度异步的call
        //异步为true，需要调度call， 同步为false， 不需要调度
        //调度方法为promoteCalls(), 先判断是否大于最大请求数，大于就返回，接着判断readyAsyncCalls是否为空，空则返回
        //不为空则便利每个readyAsyncCalls去加入到runningAsyncCalls并在线程池去执行请求。

    }
    //endregion



    //region拦截器
    private void interceptor(){
        //okhttp提供的强大机制，可以实现网络监听，请求以及响应重写，请求失败重试等功能
        //方法RealCall.getResponseWithInterceptorChain()
        //总结
        //1.创建一系列拦截器，并加入到拦截器list中,包含ApplicationInterceptor&NetworkInterceptor及okHttp内部提供的拦截器
        //2.创建拦截器链RealInterceptorChain,并执行proceed方法（proceed方法就是去创建下一个拦截器链）

        //总结2
        //1.在发起请求前 对request进行处理
        //2.调用下一个拦截器，获取response
        //3.对response进行处理，返回给上一个拦截器

        //RetryAndFollowUpInterceptor
        //1.创建StreamAllocation对象
        //2.调用RealInterceptorChain.proceed进行网络请求。
        //3.根据异常结果或者响应结果判断是否要进行重试
        //4.调用下一个拦截器，对response进行处理

        //BridgeInterceptor
        //1.负责将用户构建的一个Request请求转化为能够进行网络访问的请求
        //2.将这个能够访问的Request进行网络请求
        //3.调用下层的InterceptorChain.proceed获取response
        //3.将网络请求回来的response转化为用户可用的response，如gzip解压

    }
    //endregion
}
