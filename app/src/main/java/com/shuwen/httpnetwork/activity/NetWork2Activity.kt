package com.shuwen.httpnetwork.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RestrictTo
import com.shuwen.httpnetwork.R
import com.shuwen.httpnetwork.bean.BannerInfo
import com.shuwen.httpnetwork.request.Request
import com.shuwen.network.kt.bean.ResponseResult
import kotlinx.coroutines.*

class NetWork2Activity : AppCompatActivity(), CoroutineScope by CoroutineScope(Dispatchers.Main) {

    private val TAG = "NetWork2Activity"

    //方式一
//    private val mainScope = MainScope()
    //或者，我们可以通过委托模式来让 Activity 实现 CoroutineScope 接口，
    // 从而可以在 Activity 内直接启动协程而不必显示地指定它们的上下文，并且在 onDestroy()中自动取消所有协程

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_work2)
        init()
    }

    private fun init(){
        findViewById<Button>(R.id.btn_test_request2).setOnClickListener {
            request()
        }
    }

    private fun request(){
        launch {
            val requestHelper = Request()
            var result : ResponseResult<List<BannerInfo>>? = null
            withContext(Dispatchers.IO){
                //必须要这样写，避免网络错误出现的异常等
                try {
                    result = requestHelper.getBanner()
                }catch (e: Exception){
                    Log.i(TAG, "${e.message}")
                }

                Log.i(TAG, "-1--${result?.data?.get(0)?.title}")
            }
            Log.i(TAG, "-2--${result?.data?.get(0)?.title}")
        }

        Log.i(TAG, "---end")
    }
    /**
     * 协程练习测试
     */
    private fun testCoroutine(){
        GlobalScope.launch(Dispatchers.Main) {
            val name = getMsgByNet()
            Log.i(TAG, Thread.currentThread().name + "  $name")
        }

        Log.i(TAG, Thread.currentThread().name)

        launch{
            val name = getMsgByNet()
            Log.i(TAG, Thread.currentThread().name + "  ----$name")
        }
//            coroutineScope.cancel()
    }

    private suspend fun getMsgByNet(): String {
        var name = ""
        withContext(Dispatchers.IO){
            Thread.sleep(10000)
            name = "娃哈哈"
        }
        return name
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}