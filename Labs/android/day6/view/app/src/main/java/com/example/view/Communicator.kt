package com.example.view

import com.example.view.model.Product


interface Communicator {
    fun updateProduct(product: Product)

}