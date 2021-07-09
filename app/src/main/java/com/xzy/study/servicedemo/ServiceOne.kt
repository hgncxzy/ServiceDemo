package com.xzy.study.servicedemo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.xzy.study.servicedemo.Common.KEY

/**
 *
 * @author ：created by xzy.
 * @date ：2021/7/9
 */
class ServiceOne : Service() {
    private val tag = "ServiceOne"

    inner class MyBinder : Binder() {
        fun getService(): ServiceOne {
            return this@ServiceOne
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(tag, "onCreate")
    }

    // 必须要实现 onBind 方法，不然会报错
    override fun onBind(p0: Intent?): IBinder? {
        val data = p0?.getStringExtra(KEY)
        Log.d(tag, "onBind,receive data = $data")
        return MyBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(tag, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    fun doSomething() {
        Log.d(tag, "service one doSomething")
    }
}