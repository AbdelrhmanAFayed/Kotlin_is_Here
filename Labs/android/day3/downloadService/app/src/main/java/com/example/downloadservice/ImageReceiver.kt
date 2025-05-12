package com.example.downloadservice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity

class ImageReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        context.startActivity(Intent(context, ImageActivity::class.java).apply {addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)})
        Log.d("Receiver","In The Receiver")
    }
}