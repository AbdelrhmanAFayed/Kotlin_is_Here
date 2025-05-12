package com.example.productsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productsapp.ProductAdapter
import com.example.productsapp.R
import com.example.productsapp.model.Product


class FragmentA : Fragment() {

    lateinit var myAdapter: ProductAdapter
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView

    lateinit var products : List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        products = mutableListOf(Product(), Product(), Product())

        recyclerView = view.findViewById(R.id.recyclerProducts)
        myLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        myAdapter = ProductAdapter(products) {
            Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
        }
        recyclerView.apply {
            adapter = myAdapter
            layoutManager = myLayoutManager
        }

    }
}