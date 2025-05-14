package com.example.productsapp.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.productsapp.model.Product


@Dao
interface ProductDao {

    @Query("SELECT * FROM product_table")
    suspend fun getAll() : List<Product>

    @Insert
    suspend fun insert(product: Product) : Long

    @Delete
    suspend fun delete(product: Product) : Int

}