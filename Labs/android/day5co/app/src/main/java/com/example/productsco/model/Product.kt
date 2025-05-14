package com.example.productsco.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(

    @PrimaryKey
    val id: Int = 0,

    val title: String = "Default Product",
    val description: String = "This is the only Product you will ever need",
    val category: String = "All of them",
    val price: Double = 999.99,
    val discountPercentage: Double = 0.5,
    val rating: Double = 4.99,
    val stock: Int = 100,
    val brand: String = "My Brand",
    val sku: String = "2222",
    val weight: Double = 5.0,
    val warrantyInformation: String = "The Warranty",
    val shippingInformation: String = "The Shipping",
    val availabilityStatus: String = "On Demand",
    val returnPolicy: String = "Very",
    val minimumOrderQuantity: Int = 3,
    val thumbnail: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Cat_August_2010-4.jpg/1920px-Cat_August_2010-4.jpg"
)