package com.example.productsapp.model

data class Product(
    val id: Int = 0,
    val title: String = "Default Product",
    val description: String = "This is the only Product you will ever need",
    val category: String = "All of them",
    val price: Double = 999.99,
    val discountPercentage: Double = 0.5,
    val rating: Double = 4.99,
    val stock: Int = 100,
    val tags: List<String> = listOf<String>(),
    val brand: String = "My Brand",
    val sku: String = "2222",
    val weight: Double = 5.0,
    val warrantyInformation: String = "The Warranty",
    val shippingInformation: String = "The Shipping",
    val availabilityStatus: String = "On Demand",
    val returnPolicy: String = "Very",
    val minimumOrderQuantity: Int = 3,
    val images: List<String> = listOf<String>() ,
    val thumbnail: String = ""
)