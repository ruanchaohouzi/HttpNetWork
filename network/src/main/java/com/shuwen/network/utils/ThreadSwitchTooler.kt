package com.shuwen.network.utils

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * 线程切换工具，主要是为Observable指定订阅线程和观察线程
 * @author lilei
 * @since 2019-10-24
 * @version 1.0
 */

/**
 * 延迟观察结果
 * @param delayTime 延迟时间，单位：毫秒
 * @param <T> 数据泛型
 * @return
</T> */
fun <Any> delaySwitch(delayTime: Long): ObservableTransformer<Any, Any> {
    return ObservableTransformer { upstream ->
        upstream
            // 指定订阅线程为io线程，可做耗时事情
            .subscribeOn(Schedulers.io())
            // 指定延迟时间
            .delay(delayTime, TimeUnit.MILLISECONDS)
            // 指定观察线程为ui线程
            .observeOn(AndroidSchedulers.mainThread())
    }
}

/**
 * 正常观察结果，有结果立即处理
 * @param <T>
 * @return
</T> */
fun <T> normalSwitch(): ObservableTransformer<T, T> {
    return ObservableTransformer { upstream ->
        upstream
            // 指定订阅线程为io线程，可做耗时事情
            .subscribeOn(Schedulers.io())
            // 指定观察线程为ui线程
            .observeOn(AndroidSchedulers.mainThread())
    }
}