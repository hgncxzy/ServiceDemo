package com.xzy.study.servicedemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import com.xzy.study.servicedemo.Common.KEY

/**
 * 多个客户端绑定同一个service，如果该service已经初始化，不会调用任何生命周期方法
 * */
class SecondActivity : AppCompatActivity() {
    private val tag = "SecondActivity"
    private lateinit var myIntent: Intent
    private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val myBinder = p1 as ServiceOne.MyBinder
            val serviceOne = myBinder.getService()
            // 调用 service 暴露给客户端的方法
            serviceOne.doSomething()
            Log.d(tag, "onServiceConnected,currentThread:${Thread.currentThread().name}")
            Log.d(tag, "onServiceConnected,myBinder:$myBinder")

        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d(tag, "onServiceDisconnected,currentThread:${Thread.currentThread().name}")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.btn1).setOnClickListener {
            myIntent = Intent(this, ServiceOne::class.java)
            myIntent.putExtra(KEY, "HELLO WORLD")
            bindService(myIntent, serviceConnection, BIND_AUTO_CREATE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }
}