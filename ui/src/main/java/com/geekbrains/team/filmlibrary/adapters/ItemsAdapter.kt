package com.geekbrains.team.filmlibrary.adapters

import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter<T>(
    clickListener: OnItemSelectedListener? = null, layout: Int
) :
    GenericAdapter<T, List<T>>(clickListener, layout) {

    override val data: MutableList<T> = ArrayList()

    override fun update(items: List<T>) {
        this.data.clear()
        this.data.addAll(items)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? Binder)?.bind(
            data = data[position],
            listener = clickListener
        )
    }

    override fun getItemCount(): Int =
        data.size
}