package com.example.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("ImageURL")
fun ImageView.loadUrl(url:String){
    if (url.isNotBlank()) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}

