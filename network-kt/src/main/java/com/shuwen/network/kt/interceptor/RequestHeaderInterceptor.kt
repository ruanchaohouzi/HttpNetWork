package com.shuwen.network.interceptor

import com.shuwen.network.kt.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * 请求头，用于添加一些固定的请求头参数
 * @version 1.0
 */
class RequestHeaderInterceptor: Interceptor {
    private var ACCESS_KEY: String = "jleScjobhSMwmVYM"

    override fun intercept(chain: Interceptor.Chain): Response {
        var time = System.currentTimeMillis().toString()
        var request: Request = chain
            .request()
            .newBuilder()
                // 添加客户端类型
            .addHeader(Constants.CLIENT_TYPE, "1")
//            .addHeader(Constants.USER_ID, NetWork.getUserId())
            .addHeader(Constants.TIME_STAMP, time)
//            .addHeader(Constants.SIGNATURE, MD5Utils.md5(time + ACCESS_KEY))
            .build()
        // 发送请求
        return chain.proceed(request)
    }
}