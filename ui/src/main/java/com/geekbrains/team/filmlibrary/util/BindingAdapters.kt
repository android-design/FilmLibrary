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
            view.setImageResource(R.drawable.ic_no_poster)
        }
    }
}

@BindingAdapter("backdrop")
fun loadBackdrop(view: ImageView, url: String?) {
    url?.let {
        if (url.isNotEmpty()) {
            Picasso.get().load(url).into(view)
        } else {
            view.setImageResource(R.drawable.ic_no_backdrop)
        }
    }
}