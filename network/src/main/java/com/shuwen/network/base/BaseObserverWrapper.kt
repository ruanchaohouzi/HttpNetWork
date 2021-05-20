package com.shuwen.network.base

import io.reactivex.Observer

/**
 * 响应回调包装（订阅结果包装），由于{@link #onComplete()}大多情况并不会使用，所以为了每次都要实现该方法，
 * 则在这里进行空实现，方便项目中实现
 */
@Suppress("UNUSED")
abstract class BaseObserverWrapper<Any>: Observer<Any> {
    override fun onComplete() {
        // 该方法基本用不到，为了屏蔽掉该方法，所以在此实现空实现，如果想使用则可以覆写该方法
    }
}