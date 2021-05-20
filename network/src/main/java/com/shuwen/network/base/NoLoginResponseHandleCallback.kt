package com.shuwen.network.base

/**
 * 屏蔽登陆逻辑的服务端响应处理结果回调类
 * @version 1.0
 */
abstract class NoLoginResponseHandleCallback<Any>:
    BaseResponseHandleCallback<Any> {

    override fun login() {
        // 对于不需要必须登陆才能进行的流程，登陆方法是不需要的，为了屏蔽掉该方法，所以这里进行空实现，
        // 如果需要登陆才能进行的流程，可以覆写该方法或者直接使用{like @BaseResponseHandleCallback}，建议直接使用
    }
}