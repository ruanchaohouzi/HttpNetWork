package com.shuwen.httpnetwork.request

import com.shuwen.httpnetwork.bean.BannerInfo
import com.shuwen.network.kt.bean.ResponseResult
import com.shuwen.network.kt.core.NetworkManagerHelper

class Request : NetworkManagerHelper<Api>() {

    suspend fun getBanner(): ResponseResult<List<BannerInfo>>{
        return request().getBanner()
    }
}