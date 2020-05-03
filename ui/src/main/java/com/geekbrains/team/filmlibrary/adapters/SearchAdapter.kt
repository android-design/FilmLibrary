package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.databinding.*
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
                val binding = LandscapeSearchMovieItemBinding.inflate(inflater, parent, false)
                MovieViewHolder(binding, listener)
            }
            else -> {
                val binding = LandscapeSearchTvshowItemBinding.inflate(inflater, parent, false)
                TVShowViewHolder(binding, listener)
            }
        }
    }

    override fun getItemViewType(position: Int) =
        when (mDataList[position]) {
            is SearchedMovieView -> VIEW_TYPE_MOVIE
            else -> VIEW_TYPE_TV_SHOW
        }

    override fun getItemCount() = mDataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(movie = (mDataList[position] as SearchedMovieView))
            is TVShowViewHolder -> holder.bind(tvShow = (mDataList[position] as SearchedTVShowView))
        }
    }

    class MovieViewHolder(
        private val binding: LandscapeSearchMovieItemBinding,
        private val listener: OnItemSelectedListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: SearchedMovieView) {
            binding.listener = listener
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    class TVShowViewHolder(
        private val binding: LandscapeSearchTvshowItemBinding,
        private val listener: OnItemSelectedListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: SearchedTVShowView) {
            binding.listener = listener
            binding.tvShow = tvShow
            binding.executePendingBindings()
        }
    }
}