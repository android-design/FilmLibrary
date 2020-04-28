package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class GenericAdapter<T, L>(
    protected val clickListener: OnItemSelectedListener? = null,
    private val layout: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract val data: L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return getViewHolder(inflater, parent, viewType)
    }

    override fun getItemViewType(position: Int): Int = layout

    abstract fun update(items: L)

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