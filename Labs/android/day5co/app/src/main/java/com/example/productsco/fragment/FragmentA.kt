package com.example.productsco.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productsco.Communicator
import com.example.productsco.ProductAdapter
import com.example.productsco.R
import com.example.productsco.model.Product
import com.example.productsco.model.network.RetroFitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class   FragmentA : Fragment() {

    lateinit var myAdapter: ProductAdapter
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView
    lateinit var products : List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO)
        {
            try {

                val service = RetroFitHelper.service
                val result = service.getProducts()
                val resultProducts = result.body()?.products
                withContext(Dispatchers.Main) {
                    myAdapter.submitList(resultProducts)
                }

            }
            catch (th : Throwable)
            {
                th.printStackTrace()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        products = mutableListOf<Product>()

        recyclerView = view.findViewById(R.id.recyclerProducts)
        myLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        myAdapter = ProductAdapter {
            (activity as Communicator).updateProduct(it)
        }

        myAdapter.submitList(products)
        recyclerView.apply {
            adapter = myAdapter
            layoutManager = myLayoutManager
        }

    }
}