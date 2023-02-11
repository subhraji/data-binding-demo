package com.example.databinding.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.databinding.R

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?){
    Glide.with(view)
        .load(url)
        .placeholder(R.color.red)
        .into(view)
}