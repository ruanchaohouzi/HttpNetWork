package com.shuwen.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.shuwen.http.bean.OssInfo
import com.shuwen.http.request.VlogRequestInteraction
import com.shuwen.network.bean.ResponseResult
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        findViewById<Button>(R.id.btn_network_1).setOnClickListener {
            //获取oss信息
            VlogRequestInteraction.getOssInfo()
                .subscribe(object : Observer<ResponseResult<OssInfo>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(result: ResponseResult<OssInfo>) {
                    }

                    override fun onError(e: Throwable) {
                    }

                })
        }

        findViewById<Button>(R.id.btn_network_2).setOnClickListener {

        }
    }
}