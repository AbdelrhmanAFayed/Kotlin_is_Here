package com.example.timeservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.timeservice.services.TimeService

class MainActivity : AppCompatActivity() {

    lateinit var myService : TimeService
    var isBound : Boolean = false

    lateinit var timeTV : TextView
    lateinit var refButton: Button
    lateinit var unBindButton : Button

    private var myConnection : ServiceConnection = object : ServiceConnection
    {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            val binder : TimeService.MyLocalBinder = service as TimeService.MyLocalBinder
            myService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, TimeService::class.java)
        bindService(intent,myConnection,Context.BIND_AUTO_CREATE)

        timeTV = findViewById(R.id.timeTV)
        refButton = findViewById(R.id.refButton)
        unBindButton = findViewById(R.id.button_UNBIND)

        refButton.setOnClickListener {
            showTime()
        }
        unBindButton.setOnClickListener {

            unbindService(myConnection)
        }
    }
    fun showTime()
    {
        val currentTime : String = myService.getCurrentTime()
        Log.i("TimeService","showTime: $currentTime")
        timeTV.text = "CurrentTime is \n $currentTime"

    }

    override fun onDestroy() {
        super.onDestroy()
        myService.unbindService(myConnection)
    }
}