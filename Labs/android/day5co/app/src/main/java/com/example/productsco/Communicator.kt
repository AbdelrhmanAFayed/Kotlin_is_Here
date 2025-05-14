package com.example.productsco

import com.example.productsco.model.Product

interface Communicator {
    fun updateProduct(product: Product)

}