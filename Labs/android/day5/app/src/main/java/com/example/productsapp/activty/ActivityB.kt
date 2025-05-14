package com.example.productsapp.activty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.productsapp.Communicator
import com.examxple.productsapp.R
import com.example.productsapp.fragment.FragmentB
import com.example.productsapp.model.Product
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