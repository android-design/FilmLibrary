package com.geekbrains.team.filmlibrary.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("url")
fun loadImage(view: ImageView, url: String) {
    if (url.isNotEmpty()){
        Picasso.get().load(url).into(view)
    }
}