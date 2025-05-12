package com.example.downloadservice

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.downloadservice.services.DownloadService

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDown = findViewById<Button>(R.id.btn_Down)
        val urlTEXT = findViewById<TextView>(R.id.urlTEXT)

        btnDown.setOnClickListener {
            val intent = Intent(this@MainActivity, DownloadService::class.java)
            intent.putExtra("URL",urlTEXT.text.toString())
            DownloadService.myEnqueueWork(this,intent)
        }

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }
}