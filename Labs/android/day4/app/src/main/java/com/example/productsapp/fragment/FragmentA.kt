package com.example.productsapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.productsapp.Communicator
import com.example.productsapp.MyWorker
import com.example.productsapp.ProductAdapter
import com.example.productsapp.R
import com.example.productsapp.model.Product


class   FragmentA : Fragment() {

    lateinit var myAdapter: ProductAdapter
    lateinit var myLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView
    lateinit var products : List<Product>
    lateinit var myWorkRequest : WorkRequest
    lateinit var result : LiveData<WorkInfo>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myWorkRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
        result = WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(myWorkRequest.id)

        result.observe(this) {
            workInfo ->
            when(workInfo.state)
            {
                WorkInfo.State.SUCCEEDED ->{
                myAdapter.submitList(MyWorker.locProducts)
                }
                WorkInfo.State.ENQUEUED , WorkInfo.State.RUNNING -> {}
                WorkInfo.State.CANCELLED , WorkInfo.State.FAILED -> {}
                else -> {}
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