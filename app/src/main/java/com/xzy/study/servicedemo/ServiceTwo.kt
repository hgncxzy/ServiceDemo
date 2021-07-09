package com.xzy.study.servicedemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.xzy.study.servicedemo.Common.KEY

/**
 *
 * @author ：created by xzy.
 * @date ：2021/7/9
 */
class ServiceTwo : Service() {
    private val tag = "ServiceTwo"

    override fun onCreate() {
        super.onCreate()
        Log.d(tag, "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val data = intent?.getStringExtra(KEY)
        Log.d(tag, "onStartCommand,receive data = $data")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    // 必须要实现 onBind 方法，不然会报错
    override fun onBind(p0: Intent?): IBinder? {
        Log.d(tag, "onBind")
        return null
    }
}