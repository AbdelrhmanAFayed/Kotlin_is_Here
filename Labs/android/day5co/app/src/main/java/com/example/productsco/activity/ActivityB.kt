package com.example.productsco.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.productsco.Communicator
import com.example.productsco.R
import com.example.productsco.fragment.FragmentB
import com.example.productsco.model.Product
import com.google.gson.Gson

class ActivityB : AppCompatActivity(), Communicator {

    private lateinit var  fragmentB: FragmentB
    private lateinit var currentProduct: Product


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_b)

        fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentContainerB) as FragmentB


    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        val productJson = intent.getStringExtra("product_json")
        val gson = Gson()
        val product = gson.fromJson(productJson, Product::class.java)
        updateProduct(product)
    }

    override fun onDestroy() {

        super.onDestroy()
        finish()
    }

    override fun updateProduct(product: Product) {
        currentProduct = product
        fragmentB.updateProduct(product)
    }
}