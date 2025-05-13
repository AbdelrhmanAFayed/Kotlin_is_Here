package com.example.productsapp

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.productsapp.model.Product
import com.example.productsapp.model.ProductDiffCallback


class ProductAdapter (var listener : (Product) -> Unit ) : ListAdapter<Product, ProductAdapter.ProductHolder> (ProductDiffCallback())
{

    class ProductHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle : TextView = view.findViewById(R.id.textTitle)
        val imgProduct : ImageView = view.findViewById(R.id.imageView)
        val constraintLayout : ConstraintLayout = view.findViewById(R.id.layout_item)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item,parent,false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {

        val product = getItem(position)

        holder.txtTitle.text = product.title
        holder.imgProduct.setImageResource(R.drawable.ic_launcher_foreground)
        holder.constraintLayout.setOnClickListener { listener(product) }

    }
}
