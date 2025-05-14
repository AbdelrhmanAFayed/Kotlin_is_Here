package com.example.productsapp.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.productsapp.model.Product

@Database(entities = arrayOf(Product::class), version = 1)
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