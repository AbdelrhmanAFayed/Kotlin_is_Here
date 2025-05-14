package com.example.productsco.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.productsco.R
import com.example.productsco.model.Product
import com.google.gson.Gson

class FragmentB : Fragment() {
    companion object
    {
        private var currentProduct: Product? = null
    }

    private lateinit var productImage: ImageView
    private lateinit var productTitle: TextView
    private lateinit var productPrice: TextView
    private lateinit var productRating: RatingBar
    private lateinit var productDescription: TextView



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
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productImage = view.findViewById(R.id.productImage)
        productTitle = view.findViewById(R.id.productTitle)
        productPrice = view.findViewById(R.id.productPrice)
        productRating = view.findViewById(R.id.productRating)
        productDescription = view.findViewById(R.id.productDescription)

        currentProduct?.let { updateProduct(it) }

    }

    fun updateProduct(product: Product) {
        currentProduct = product
        productTitle.text = product.title
        productPrice.text = product.price.toString()
        productDescription.text = product.description
        productRating.rating = product.rating.toFloat()

        if (product.thumbnail.isNotEmpty()) {
            Glide.with(this)
                .load(product.thumbnail)
                .into(productImage)
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