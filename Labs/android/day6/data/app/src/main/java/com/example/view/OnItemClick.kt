package com.example.view

import com.example.view.model.Product

fun interface OnItemClick {
    fun onClick(product : Product )
}