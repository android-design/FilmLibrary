package com.geekbrains.team.filmlibrary.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.geekbrains.team.filmlibrary.R
import com.squareup.picasso.Picasso


@BindingAdapter("poster")
fun loadPoster(view: ImageView, url: String?) {
    url?.let {
        if (url.isNotEmpty()) {
            Picasso.get().load(url).into(view)
        }
        else {
            Picasso.get().load(R.drawable.ic_no_poster).placeholder(R.drawable.ic_no_poster).into(view)
        }
    }
}

@BindingAdapter("backdrop")
fun loadBackdrop(view: ImageView, url: String?) {
    url?.let {
        if (url.isNotEmpty()) {
            Picasso.get().load(url).into(view)
        } else {
            Picasso.get().load(R.drawable.ic_no_backdrop).placeholder(R.drawable.ic_no_backdrop).into(view)
        }
    }
}