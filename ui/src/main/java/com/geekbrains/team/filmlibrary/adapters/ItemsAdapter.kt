package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.Const.PRELOAD_FROM_SERVER_ITEMS_COYUNT

class ItemsAdapter<T>(
    val clickListener: OnItemSelectedListener? = null,
    val layout: Int,
    val onScrollToLastPageListener: (() -> Unit)? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val data: MutableList<T> = ArrayList()
    var isAllItemsLoaded: Boolean = false

    fun update(items: List<T>) {
        this.data.clear()
        this.data.addAll(items)
    }

    fun addItems(items: List<T>) {
        this.data.addAll(items)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (!isAllItemsLoaded && position >= data.size - PRELOAD_FROM_SERVER_ITEMS_COYUNT) {
            onScrollToLastPageListener?.invoke()
        }

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