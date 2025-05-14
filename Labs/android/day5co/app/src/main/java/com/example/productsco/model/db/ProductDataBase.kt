package com.example.productsco.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.productsco.model.Product

@Database(entities = [Product::class], version = 1 , exportSchema = false)
abstract class ProductDataBase : RoomDatabase() {

    abstract fun getProductDao() : ProductDao

    companion object
    {
        @Volatile
        private var INSTANCE : ProductDataBase? = null

        fun getInstance( context: Context) : ProductDataBase
        {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    ProductDataBase::class.java,"product_table")
                    .build()
                INSTANCE = instance
                return instance
            }
        }


    }
}