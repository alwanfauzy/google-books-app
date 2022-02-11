package com.alwan.googlebooksapp.utils

import android.widget.ImageView
import com.alwan.googlebooksapp.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.book_placeholder)
        .into(this)
}

fun ArrayList<String>.toBetterString(): String {
    var result = ""

    this.forEachIndexed { index, value ->
        result = if (index != this.size - 1) "$value, " else value
    }

    return result
}