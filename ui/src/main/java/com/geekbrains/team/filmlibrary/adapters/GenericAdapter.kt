package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView

abstract class GenericAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    var itemList = mutableListOf<T>()
    var item: T? = null
    private var clickListener: OnItemSelectedListener? = null

    constructor()

    constructor(listener: OnItemSelectedListener) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return getViewHolder(inflater, parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (item == null) (holder as Binder<T>).bind(itemList[position], null, clickListener)
        else (holder as Binder<T>).bind(item  as T, (item as MovieView).images[position], clickListener)
    }

    override fun getItemCount(): Int {
        return if (item == null) itemList.size
        else {
            if (item is MovieView) (item as MovieView).images.size
            else (item as TVShowView).images.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return item?.let { getLayoutId(position, it) } ?: getLayoutId(position, itemList[position])
    }

    fun update(items: List<T>) {
        itemList = items.toMutableList()
        notifyDataSetChanged()
    }

    fun updateOneItem(oneItem: T) {
        item = oneItem
    }

    protected abstract fun getLayoutId(position: Int, obj: T): Int

    protected open fun getViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.create(inflater, parent, viewType)
    }

    internal interface Binder<T> {
        fun bind(data: T, list: String? = null, listener: OnItemSelectedListener?)
    }
}