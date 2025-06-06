package com.example.view.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.view.Communicator
import com.example.view.R
import com.example.view.databinding.ActivityMainBinding
import com.example.view.fragment.FragmentA
import com.example.view.fragment.FragmentB
import com.example.view.model.Product
import com.google.gson.Gson

class ActivityA : AppCompatActivity() , Communicator {

    private lateinit var fragmentA: FragmentA
    private var fragmentB: FragmentB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fragmentB = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        { supportFragmentManager.findFragmentById(R.id.fragment_container_b) as? FragmentB }
        else{ null }
    }

    override fun updateProduct(product: Product) {
        if (fragmentB != null) {
            Log.d("ActivityA", "Updating FragmentB with product: ${product.title}")
            fragmentB?.updateProduct(product)
        } else {
            Log.d("ActivityA", "FragmentB is null, converting product to JSON and starting ActivityB")
            val gson = Gson()
            val productJson = gson.toJson(product)
            val intent = Intent(this, ActivityB::class.java).apply {
                putExtra("product_json", productJson)
            }
            startActivity(intent)
        }
    }


}