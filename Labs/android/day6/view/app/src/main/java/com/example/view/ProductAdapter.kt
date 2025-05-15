package com.example.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.view.databinding.ItemBinding
import com.example.view.model.Product
import com.example.view.model.ProductDiffCallback

class ProductAdapter (var listener : (Product) -> Unit ) : ListAdapter<Product, ProductAdapter.ProductHolder>(ProductDiffCallback())
{
    lateinit var binding: ItemBinding

    class ProductHolder(var binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        binding = ItemBinding.inflate(inflater,parent,false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {

        val product = getItem(position)

        binding.textTitle.text = product.title
        Glide.with(binding.imageView.context).load(product.thumbnail).into(binding.imageView)
        binding.layoutItem.setOnClickListener { listener(product) }

    }
}
