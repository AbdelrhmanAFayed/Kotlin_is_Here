package com.example.productsapp

import com.example.productsapp.model.Product

interface ProductListener {

    fun onProductClick(product : Product)

}