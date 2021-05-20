package com.shuwen.http.request

import com.shuwen.http.bean.OssInfo
import com.shuwen.network.bean.ResponseResult
import com.shuwen.network.core.NetworkManagerHelper
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

object VlogRequestInteraction : NetworkManagerHelper<VLogApi?>() {

    private val TAG = VlogRequestInteraction::class.java.simpleName

    /**
     * 获取Oss信息
     */
    fun getOssInfo(): Observable<ResponseResult<OssInfo>> {
        return request()!!.ossInfo.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
    }

}