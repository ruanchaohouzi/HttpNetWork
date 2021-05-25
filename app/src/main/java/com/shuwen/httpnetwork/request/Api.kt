package com.shuwen.httpnetwork.request

import com.shuwen.httpnetwork.bean.BannerInfo
import com.shuwen.network.kt.bean.ResponseResult
import retrofit2.http.GET

interface Api {

    @GET("banner/json")
    suspend fun getBanner(): ResponseResult<List<BannerInfo>>
}