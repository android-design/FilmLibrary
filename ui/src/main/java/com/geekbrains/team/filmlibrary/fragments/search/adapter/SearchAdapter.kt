package com.geekbrains.team.filmlibrary.fragments.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.databinding.SearchMovieItemRvBinding
import com.geekbrains.team.filmlibrary.databinding.SearchTvItemRvBinding
import com.geekbrains.team.filmlibrary.fragments.search.model.SearchView
import com.geekbrains.team.filmlibrary.fragments.search.model.SearchedMovieView
import com.geekbrains.team.filmlibrary.fragments.search.model.SearchedTVShowView

class SearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MOVIE = 0
        const val VIEW_TYPE_TV_SHOW = 1
    }

    val mDataList: MutableList<SearchView> = ArrayList()

    fun setData(data: List<SearchView>) {
        mDataList.clear()
        mDataList.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_MOVIE -> {
                val binding = SearchMovieItemRvBinding.inflate(inflater, parent, false)
                MovieViewHolder(binding)
            }
            else -> {
                val binding = SearchTvItemRvBinding.inflate(inflater, parent, false)
                TVShowViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (mDataList[position]) {
            is SearchedMovieView -> VIEW_TYPE_MOVIE
            else -> VIEW_TYPE_TV_SHOW
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(movie = (mDataList[position] as SearchedMovieView))
            is TVShowViewHolder -> holder.bind(tvshow = (mDataList[position] as SearchedTVShowView))
        }
    }

    class MovieViewHolder(private val binding: SearchMovieItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: SearchedMovieView) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    class TVShowViewHolder(private val binding: SearchTvItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvshow: SearchedTVShowView) {
            binding.tvshow = tvshow
            binding.executePendingBindings()
        }
    }
}