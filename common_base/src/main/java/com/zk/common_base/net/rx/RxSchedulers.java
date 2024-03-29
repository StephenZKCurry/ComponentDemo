package com.zk.common_base.net.rx;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @description: 通用的Rx线程转换类
 * @author: zhukai
 * @date: 2018/3/6 10:10
 */
public class RxSchedulers {
    /**
     * compose()里接收一个Transformer对象，ObservableTransformer
     * 可以通过它将一种类型的Observable转换成另一种类型的Observable。
     * 现在.subscribeOn(Schedulers.io()) .observeOn(AndroidSchedulers.mainThread())
     * 的地方可以用.compose(RxSchedulersHelper.io_main())代替。
     */
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> FlowableTransformer<T, T> applyFlowableSchedulers() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
