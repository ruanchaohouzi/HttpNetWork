package com.shuwen.network.kt.core

import com.shuwen.network.kt.Constants
import com.shuwen.network.kt.HttpInterceptorLogger
import com.shuwen.network.interceptor.RequestHeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 网络请求管理工具
 * @author ruanchao
 * @version 1.0
 */
class NetWorkManager private constructor() {

    private lateinit var mRetrofit: Retrofit
    private val TAG = NetWorkManager::class.java.simpleName

    companion object {
        private var mNetWorkManager: NetWorkManager? = null
        fun getInstance(): NetWorkManager {

            if (mNetWorkManager == null) {
                synchronized(this) {
                    if (mNetWorkManager == null) {
                        mNetWorkManager =
                            NetWorkManager()
                    }
                }
            }
            return mNetWorkManager!!
        }
    }

    init {
        //addInterceptor  只执行一次
        //addNetworkInterceptor  可能会执行对次，例如重定向， 这样获取能多次请求的消息
        //设置 请求的缓存的大小跟位置
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpInterceptorLogger())
        val okHttpClient: OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(RequestHeaderInterceptor())
            .addNetworkInterceptor(
                httpLoggingInterceptor.apply {
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

        mRetrofit = buildRetrofit(okHttpClient, Constants.API_URL)
    }

//    fun getApi(): BaseApi = mRetrofit!!.create(BaseApi::class.java)

    private fun buildRetrofit(okHttpClient: OkHttpClient, baseUrl: String) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    public fun getRetrofit(): Retrofit {
        return mRetrofit
    }

    // todo 增加切换url功能
}