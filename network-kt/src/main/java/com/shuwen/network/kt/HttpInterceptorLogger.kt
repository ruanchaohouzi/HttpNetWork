package com.shuwen.network.kt

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

/**
 * 请求和响应拦截器日志工具
 * @author ruanchao
 * @version 1.0
 */
class HttpInterceptorLogger: HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.i("okhttp", message)
    }
}