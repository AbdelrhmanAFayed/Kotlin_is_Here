package com.example.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.view.Communicator
import com.example.view.ProductAdapter
import com.example.view.R
import com.example.view.databinding.ActivityMainBinding
import com.example.view.databinding.FragmentABinding
import com.example.view.model.Product
import com.example.view.model.db.ProductDataBase
import com.example.view.model.network.RetroFitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class   FragmentA : Fragment() {

    lateinit var binding: FragmentABinding

    lateinit var myAdapter: ProductAdapter
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var products : List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentABinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        products = emptyList()

        myLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        myAdapter = ProductAdapter { (activity as Communicator).updateProduct(it) }

        myAdapter.submitList(products)

        binding.recyclerProducts.apply {
            adapter = myAdapter
            layoutManager = myLayoutManager
        }
        lifecycleScope.launch(Dispatchers.IO)
        {
            try {

                val service = RetroFitHelper.service
                val result = service.getProducts()


                if(result.isSuccessful)
                {
                    var resultProducts = result.body()?.products
                    if (resultProducts != null) {
                        for (element in resultProducts) {
                            ProductDataBase.getInstance(requireContext()).getProductDao().insert(element)

                        }
                    }
                    withContext(Dispatchers.Main) {
                        Log.d("From Network", "The number of products in API ${resultProducts?.size} ")
                        myAdapter.submitList(resultProducts)
                        Log.i("TAG", "Products: ${myAdapter.itemCount}")
                    }
                }
            }
            catch (th : Throwable)
            {
                var resultProducts = ProductDataBase.getInstance(requireContext()).getProductDao().getAll()
                withContext(Dispatchers.Main) {
                    Log.d("From DataBase", "The number of products in ROOM ${resultProducts.size} ")
                    myAdapter.submitList(resultProducts)


                }
            }
        }
    }
}