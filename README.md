



# Cybird

## 介绍

> 项目中95%的代码是由Kotlin编写
> 
> 项目对一些Base做了比较多得封装，尤其是RecyclerView的列表，封装的比较好，开发有一些列表界面整个界面代码不超过100行
>
> 项目采用的是MVVM+P的开发模式，用的技术有`RxJava`，`Retrofit`， `Dagger2`,`ConstraintLayout`, `DataBinding`等等。
>
> 项目中用到的API有`一个`，`豆瓣`，`知乎`,`GankIO`的API
>
> 准备集成Firebase，目前集成了FirebaseAuth，手机翻墙才能做登录



下载地址：

<img src="https://github.com/iBotasky/Cybird/blob/master/images/1.png" width="426px"/>

## 概览

### SplashActivity
<img src="https://github.com/iBotasky/Cybird/blob/master/images/1.jpeg" width="426px"/>

### OneFragment

接入一个App的每天首页的接口，获取最近7天首页的数据并展示，因为接口不是分页，所以通过获取当前日期去计算过去7天的日期，再通过接口分别去获取7天的首页数据封装成一个List做展示。

<img src="https://github.com/iBotasky/Cybird/blob/master/images/2.jpeg" width="426px" />



### MovieFragment

接入豆瓣电影的API，分为三个Tab，豆瓣250， 最近热门和即将上映。

<img src="https://github.com/iBotasky/Cybird/blob/master/images/3.jpeg" width="426px"/>



### MovieDetailActivity

获取一部电影的数据并做展示，豆瓣并不开放评论数据，所以在评论上没做处理。

<img src="https://github.com/iBotasky/Cybird/blob/master/images/4.jpeg" width="426px"/>



### ZhiHuFragment

调用的知乎日报的API去获取当天的数据，同样不是分页，所以只展示了今天的日报数据。

<img src="https://github.com/iBotasky/Cybird/blob/master/images/5.jpeg" width="426px"/>



### ZhiHuDetailActivity

知乎日报的详情页，下面的内容是用WebView做展示，通过获取官方的html、css模板在填充数据进去，功能跟知乎日报的内容页相似

<img src="https://github.com/iBotasky/Cybird/blob/master/images/6.jpeg" width="426px"/>



### GirlsFragment

GANKIO的妹子接口，图片加了一层灰色渐变的效果美化一下。

<img src="http://oktzkaa8p.bkt.clouddn.com/07.jpeg" style="zoom:50%" />



### GirlsDetailActivity

用第三方框架PhotoView+ViewPagger做展示，并做下载操作。

<img src="http://oktzkaa8p.bkt.clouddn.com/08.jpeg" style="zoom:50%" />

### Drawer

Blog会调用系统的浏览器跳到本人的Blog，登录那边还在开发，用的是Google的FirebaseAuth，如果在未用梯子的情况下会过不去。

<img src="http://oktzkaa8p.bkt.clouddn.com/09.jpeg" style="zoom:50%" />


### FirebaseAuth
<img src="http://oktzkaa8p.bkt.clouddn.com/10.jpeg" style="zoom:50%" />





## Code

封装完后的一个Movie的列表实现代码：

```kotlin
class MovieTopFragment : BaseRecyclerFragment<Film, MovieTopAdapter.ViewHolder>() {

    @Inject
    lateinit var mPresenter: MovieTopPresenter

    lateinit var mMovieTopBinding: FragmentMovieTopBinding


    override fun setupViews() {
        super.setupViews()
        mMovieTopBinding = getBaseViewBinding()
        mRecyclerView.addItemDecoration(getVerticalSpaceDecoration())
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun getAdapter(): BaseQuickAdapter<Film, MovieTopAdapter.ViewHolder> {
        return MovieTopAdapter()
    }

    override fun loadData() {
        mPresenter.getTopFilms(mStart)
                .compose(bindToLifecycle())
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe(
                        { filmData -> showResults(filmData.films) },
                        { e -> mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR },
                        { refreshEnd() }

                )
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_movie_top
    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }
}
```

