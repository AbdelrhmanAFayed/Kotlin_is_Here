package com.example.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.view.R
import com.example.view.databinding.FragmentABinding
import com.example.view.databinding.FragmentBBinding
import com.example.view.model.Product
import com.google.gson.Gson

class FragmentB : Fragment() {

    lateinit var binding: FragmentBBinding

    companion object
    {
        private var currentProduct: Product? = null
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { bundle ->
            val productJson = bundle.getString("product_json")
            if (productJson != null) {
                try {
                    val gson = Gson()
                    currentProduct = gson.fromJson(productJson, Product::class.java)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            else currentProduct?.let { updateProduct(it) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBBinding.inflate(inflater,container,false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        currentProduct?.let { updateProduct(it) }

    }

    fun updateProduct(product: Product) {
        currentProduct = product
        binding.productTitle.text = product.title
        binding.productPrice.text = product.price.toString()
        binding.productDescription.text = product.description
        binding.productRating.rating = product.rating.toFloat()

        if (product.thumbnail.isNotEmpty()) {
            Glide.with(this)
                .load(product.thumbnail)
                .into(binding.productImage)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentProduct?.let { product ->
            val gson = Gson()
            val productJson = gson.toJson(product)
            outState.putString("product_json", productJson)
        }
    }
}