package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.model.MovieView

class ItemsAdapter<T, S>(
    val clickListener: S? = null, val layout: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val data: MutableList<T> = ArrayList()

    fun update(items: List<T>) {
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

    override fun getItemViewType(position: Int): Int = layout

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolderFactory.create(inflater, parent, viewType)
    }
}