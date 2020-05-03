package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView

class ImagesAdapter<T>(
    val clickListener: OnItemSelectedListener? = null, val layout: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: T? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? Binder)?.bind(
            data = data,
            position = position,
            listener = clickListener
        )
    }

    override fun getItemViewType(position: Int): Int = layout

    override fun getItemCount(): Int =
        when (data) {
            is MovieView -> (data as MovieView).images.size
            is TVShowView -> (data as TVShowView).images.size
            else -> 0
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolderFactory.create(inflater, parent, viewType)
    }
}