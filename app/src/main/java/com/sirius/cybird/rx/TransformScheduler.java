package com.sirius.cybird.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava调度类，用Kotlin暂时不知道怎么写
 */
public class TransformScheduler {
    static final ObservableTransformer threadSchedulerTransform = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    static final ObservableTransformer ioSchedulerTransform = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    static final ObservableTransformer calculaterSchedulerTransform = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> ObservableTransformer<T, T> applyNewThreadScheduler() {
        return (ObservableTransformer<T, T>) threadSchedulerTransform;
    }

    public static <T> ObservableTransformer<T, T> applyIOScheduler() {
        return (ObservableTransformer<T, T>) ioSchedulerTransform;
    }
    public static <T> ObservableTransformer<T, T> applyCalculaterScheduler() {
        return (ObservableTransformer<T, T>) calculaterSchedulerTransform;
    }

}
