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

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
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
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn1).setOnClickListener {
            myIntent = Intent(this, ServiceTwo::class.java)
            myIntent.putExtra(KEY, "HELLO WORLD2")
            startService(myIntent)
        }
        findViewById<Button>(R.id.btn2).setOnClickListener {
            stopService(myIntent)
        }
        findViewById<Button>(R.id.btn3).setOnClickListener {
            myIntent = Intent(this, ServiceOne::class.java)
            myIntent.putExtra(KEY, "HELLO WORLD")
            bindService(myIntent, serviceConnection, BIND_AUTO_CREATE)
        }
        findViewById<Button>(R.id.btn4).setOnClickListener {
            unbindService(serviceConnection)
        }
        findViewById<Button>(R.id.btn5).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}