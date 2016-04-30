package com.sko4;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yevgenderkach on 4/29/16.
 */
public final class RxUtil {
    private static <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private static <T> Observable.Transformer<T, T> applyMainThreadSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> Observable<T> applySchedulers(Observable<T> observable) {
        return (Observable<T>) observable.compose(applySchedulers());
    }

    public static <T> Observable<T> applyMainThreadSchedulers(Observable<T> observable) {
        return (Observable<T>) observable.compose(applyMainThreadSchedulers());
    }
}
