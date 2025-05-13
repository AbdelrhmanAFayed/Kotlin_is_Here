package com.example.productsapp

import com.example.productsapp.model.Product

interface Communicator {
    fun updateProduct(product: Product)

}