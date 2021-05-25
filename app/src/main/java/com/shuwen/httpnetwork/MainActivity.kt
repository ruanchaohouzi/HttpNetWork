package com.shuwen.httpnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.shuwen.httpnetwork.activity.NetWork2Activity
import com.shuwen.httpnetwork.activity.NetWorkActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_request).setOnClickListener {
            startActivity(Intent(this@MainActivity, NetWorkActivity::class.java))
        }

        findViewById<Button>(R.id.btn_kt_request).setOnClickListener {
            startActivity(Intent(this@MainActivity, NetWork2Activity::class.java))
        }
    }
}