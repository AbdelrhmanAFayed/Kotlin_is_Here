package com.example.timeservice.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TimeService : Service() {
    private val myBinder : IBinder = MyLocalBinder()

    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }
    fun getCurrentTime() : String{
        val dateFormat = SimpleDateFormat("HH:mm:ss MM/dd/yyyy",Locale.US)
        return dateFormat.format(Date())
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("TimeService","Unbound Service")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TimeService","Destroyed Service")
    }

    inner class MyLocalBinder : Binder()
    {
        fun getService(): TimeService
        {
            return this@TimeService
        }

    }
}