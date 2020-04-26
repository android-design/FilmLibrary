package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView

class GenericAdapter<T>(
    private var clickListener: OnItemSelectedListener? = null,
    private val layout: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val itemList: ArrayList<T> = ArrayList()
    private var item: T? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return getViewHolder(inflater, parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (item) {
            null -> (holder as? Binder)?.bind(
                data = itemList[position],
                listener = clickListener
            )

            else -> (holder as? Binder)?.bind(
                data = item,
                position = position,
                listener = clickListener
            )
        }
    }

    override fun getItemCount(): Int =
        if (item == null) itemList.size
        else {
            when (item) {
                is MovieView -> (item as MovieView).images.size
                is TVShowView -> (item as TVShowView).images.size
                else -> 0
            }
        }

    override fun getItemViewType(position: Int): Int = layout

    fun update(items: List<T>) {
        itemList.clear()
        itemList.addAll(items)
    }

    fun updateOneItem(oneItem: T) {
        item = oneItem
    }

    private fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        ViewHolderFactory.create(inflater, parent, viewType)

    internal interface Binder {
        fun <T> bind(data: T, position: Int = 0, listener: OnItemSelectedListener?)
    }
}