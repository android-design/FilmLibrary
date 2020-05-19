package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView

class ItemsAdapterSearch<T>(
    val clickListener: OnItemSelectedListener? = null,
    val layout: Int,
    comparator: DiffUtil.ItemCallback<T>
) :
    ListAdapter<T, RecyclerView.ViewHolder>(comparator) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? Binder)?.bind(
            data = getItem(position),
            listener = clickListener
        )
    }

    override fun getItemViewType(position: Int): Int = layout

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolderFactory.create(inflater, parent, viewType)
    }

    companion object {

        val COMPARATOR_TVSHOW = object : DiffUtil.ItemCallback<TVShowView>() {
            override fun areItemsTheSame(oldItem: TVShowView, newItem: TVShowView): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TVShowView, newItem: TVShowView): Boolean =
                oldItem == newItem
        }

        val COMPARATOR_MOVIE = object : DiffUtil.ItemCallback<MovieView>() {
            override fun areItemsTheSame(oldItem: MovieView, newItem: MovieView): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieView, newItem: MovieView): Boolean =
                oldItem == newItem
        }
    }
}