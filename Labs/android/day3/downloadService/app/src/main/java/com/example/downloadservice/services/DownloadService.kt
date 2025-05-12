package com.example.downloadservice.services

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.core.app.JobIntentService
import com.example.downloadservice.ImageActivity
import com.example.downloadservice.ImageReceiver
import java.io.IOException
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class DownloadService() : JobIntentService() {
    lateinit var receiver : ImageReceiver

    companion object
    {
     const val JOB_ID = 1
        fun myEnqueueWork(context: Context, work : Intent)
        {
            enqueueWork(context,DownloadService::class.java,JOB_ID,work)
        }
    }
    override fun onHandleWork(intent: Intent) {
        receiver  = ImageReceiver()
        val intentFilter = IntentFilter("com.example.downloadservice.ACTION_DOWNLOAD_DONE")
        registerReceiver(receiver,intentFilter,RECEIVER_EXPORTED)

        val url = intent.getStringExtra("URL").toString()
        Log.d("Inside the service",url )
        save(download(url))
        broadcastIntent()
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun onDestroy() {
        super.onDestroy()
         unregisterReceiver(receiver)

    }

    fun broadcastIntent()
    {
        sendBroadcast(Intent().apply {
            action = "com.example.downloadservice.ACTION_DOWNLOAD_DONE"
            addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        })
    }


    @Throws(IOException::class)
    private fun download(url: String): Bitmap? {
        val imgURL = URL(url)

        val connection = imgURL.openConnection() as HttpsURLConnection

        connection.setRequestMethod("GET")

        connection.connect()

        val RespondCode = connection.getResponseCode()

        val `is` = connection.getInputStream()

        return BitmapFactory.decodeStream(`is`)
    }
    private fun save(image: Bitmap?)
    {
        val fos = this.openFileOutput("MyImage",MODE_PRIVATE)
        image?.compress(Bitmap.CompressFormat.PNG,100,fos)
        fos.close()
    }



}