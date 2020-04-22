package com.geekbrains.team.filmlibrary.fragments.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
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

    private var listener: OnItemSelectedListener? = null

    fun attachListener(listener: OnItemSelectedListener?) {
        this.listener = listener
    }

    fun setData(data: List<SearchView>) {
        mDataList.clear()
        mDataList.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_MOVIE -> {
                val binding = SearchMovieItemRvBinding.inflate(inflater, parent, false)
                MovieViewHolder(binding, delegate = listener)
            }
            else -> {
                val binding = SearchTvItemRvBinding.inflate(inflater, parent, false)
                TVShowViewHolder(binding, delegate = listener)
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

    class MovieViewHolder(
        private val binding: SearchMovieItemRvBinding,
        private val delegate: OnItemSelectedListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        var listener: OnItemSelectedListener? = null

        fun bind(movie: SearchedMovieView) {
            binding.listener = delegate
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    class TVShowViewHolder(
        private val binding: SearchTvItemRvBinding,
        private val delegate: OnItemSelectedListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        var listener: OnItemSelectedListener? = null

        fun bind(tvshow: SearchedTVShowView) {
            binding.listener = delegate
            binding.tvshow = tvshow
            binding.executePendingBindings()
        }
    }
}