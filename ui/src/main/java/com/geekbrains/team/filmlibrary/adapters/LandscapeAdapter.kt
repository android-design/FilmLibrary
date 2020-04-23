package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.databinding.LandscapeCardItemBinding
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView
import java.util.*

class LandscapeAdapter: RecyclerView.Adapter<LandscapeAdapter.LandscapeHolder>() {
    var movie: MutableList<MovieView> = ArrayList()
    var tvShow: MutableList<TVShowView> = ArrayList()
    private var listener: OnItemSelectedListener? = null

    fun attachListener(listener: OnItemSelectedListener?) {
        this.listener = listener
    }

    fun setMovies(data: List<MovieView>) {
        movie.clear()
        movie.addAll(data)
    }

    fun setTVShows(data: List<TVShowView>) {
        tvShow.clear()
        tvShow.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandscapeHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LandscapeCardItemBinding.inflate(inflater, parent, false)
        return LandscapeHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return if (movie.isNotEmpty()) movie.size else tvShow.size
    }

    override fun onBindViewHolder(holder: LandscapeHolder, position: Int) {
        if (movie.isNotEmpty()) holder.bindMovie(movie[position])
        else holder.bindTVShow(tvShow[position])
    }

    class LandscapeHolder(
        private val binding: LandscapeCardItemBinding,
        private val delegate: OnItemSelectedListener?
    ): RecyclerView.ViewHolder(binding.root) {
        val listener: OnItemSelectedListener? = null

        fun bindMovie(movie: MovieView?) {
            binding.listener = delegate
            binding.movie = movie
            binding.executePendingBindings()
        }

        fun bindTVShow(movie: TVShowView?) {
            binding.listener = delegate
            binding.tvShow = movie
            binding.executePendingBindings()
        }
    }
}