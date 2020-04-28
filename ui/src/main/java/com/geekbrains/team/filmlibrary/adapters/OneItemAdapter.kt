package com.geekbrains.team.filmlibrary.adapters

import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView

class OneItemAdapter<T>(
    clickListener: OnItemSelectedListener? = null, layout: Int
) :
    GenericAdapter<T, T?>(clickListener, layout) {

    override var data: T? = null

    override fun update(items: T?) {
        this.data = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? Binder)?.bind(
            data = data,
            position = position,
            listener = clickListener
        )
    }

    override fun getItemCount(): Int = when (data) {
        is MovieView -> (data as MovieView).images.size
        is TVShowView -> (data as TVShowView).images.size
        else -> 0
    }
}