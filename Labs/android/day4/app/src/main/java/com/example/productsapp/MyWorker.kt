package com.example.productsapp

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.productsapp.model.Product
import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONArray
import org.json.JSONObject


class MyWorker(context: Context , workerParameters: WorkerParameters) : Worker(context,workerParameters) {

    companion object
    {
        val locProducts = mutableListOf<Product>()
    }

    override fun doWork(): Result {
        val url = URL("https://dummyjson.com/products")
        val connection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.connectTimeout = 10000
        connection.readTimeout = 10000

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val jsonString = connection.inputStream.bufferedReader().use { it.readText() }
            val jsonObj = JSONObject(jsonString)
            val productsArray = jsonObj.getJSONArray("products")
            locProducts.clear()
            for (i in 0 until productsArray.length()) {
                var c = productsArray.getJSONObject(i)
                locProducts.add(Product(title = c.getString("title")
                    ,description = c.getString("description")
                    ,thumbnail = c.getString("thumbnail")
                    ,price = c.getDouble("price")
                    ,rating = c.getDouble("rating"))
                )
            }

            return Result.success()
        }

        return Result.failure()

    }
}