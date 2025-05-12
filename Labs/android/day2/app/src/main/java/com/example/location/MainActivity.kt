package com.example.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    lateinit var txtLong : TextView
    lateinit var txtLat : TextView
    lateinit var txtAdd : TextView

    lateinit var btnSMS : Button
    lateinit var btnMap : Button

    lateinit var address : String

    val LOCATION_PERM_ID = 1
    lateinit var callback: LocationCallback

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    val request = LocationRequest.Builder(0).apply {
        setPriority(Priority.PRIORITY_HIGH_ACCURACY)
    }.build()



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtAdd = findViewById(R.id.textAdd)
        txtLong = findViewById(R.id.textLong)
        txtLat = findViewById(R.id.textLat)

        btnSMS = findViewById(R.id.btnSMS)
        btnMap = findViewById(R.id.btnMap)


        btnSMS.setOnClickListener {

            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.data = "sms: 01091646183".toUri()

            smsIntent.putExtra("sms_body", "Hi, My Current Location is $address")
            startActivity(smsIntent)
        }


        callback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)
                Log.d("LocationApp",p0.lastLocation.toString())
                var long = p0.lastLocation?.longitude ?: 0.0
                var lat = p0.lastLocation?.latitude ?: 0.0

                txtLong.text = long.toString()
                txtLat.text = lat.toString()

                val coder = Geocoder(this@MainActivity)

                address = coder.getFromLocation(lat , long ,1)?.get(0)?.getAddressLine(0).toString()
                txtAdd.text = address

            }
        }
        requestLocationUpdates(request,callback)


    }

    fun requestLocationUpdates (request: LocationRequest, callback: LocationCallback)
    {
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
               ActivityCompat.requestPermissions(this,arrayOf(Manifest.permission.ACCESS_FINE_LOCATION
                   ,Manifest.permission.ACCESS_COARSE_LOCATION)
                   ,LOCATION_PERM_ID)
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(request, callback, Looper.myLooper())

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("LocationApp","First Overide")
        if (requestCode == LOCATION_PERM_ID)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getLocation()
            }
            else
            {
                Toast.makeText(this,"Location Required", Toast.LENGTH_LONG).show()
            }

        }

    }


    @SuppressLint("MissingPermission")
    fun getLocation()
    {
        fusedLocationProviderClient.requestLocationUpdates(request, callback, Looper.myLooper())


    }

}