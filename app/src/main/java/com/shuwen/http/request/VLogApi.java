package com.shuwen.http.request;

import com.shuwen.http.bean.OssInfo;
import com.shuwen.network.bean.ResponseResult;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


public interface VLogApi {

    @POST("censor/api/shanlv/oss/getToken")
    Observable<ResponseResult<OssInfo>> getOssInfo();

}
