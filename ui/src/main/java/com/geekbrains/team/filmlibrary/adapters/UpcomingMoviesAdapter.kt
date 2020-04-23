package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.databinding.UpcomingSmallCardItemBinding
import com.geekbrains.team.filmlibrary.fragments.mainScreen.model.UpcomingMovieView
import com.geekbrains.team.filmlibrary.model.TVShowView
import java.util.*

class UpcomingMoviesAdapter: RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingCardHolder>()  {
    var movie: MutableList<UpcomingMovieView> = ArrayList()
    var tvShow: MutableList<TVShowView> = ArrayList()
    private var listener: OnItemSelectedListener? = null

    fun attachListener(listener: OnItemSelectedListener?) {
        this.listener = listener
    }

    fun setMovies(data: List<UpcomingMovieView>) {
        movie.clear()
        movie.addAll(data)
    }

    fun setTVShows(data: List<TVShowView>) {
        tvShow.clear()
        tvShow.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingCardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UpcomingSmallCardItemBinding.inflate(inflater, parent, false)
        return UpcomingCardHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return if (movie.isNotEmpty()) movie.size else tvShow.size
    }

    override fun onBindViewHolder(holder: UpcomingCardHolder, position: Int) {
        if (movie.isNotEmpty()) holder.bindMovie(movie[position])
        else holder.bindTVShow(tvShow[position])
    }

    class UpcomingCardHolder(
        private val binding: UpcomingSmallCardItemBinding,
        private val delegate: OnItemSelectedListener?
    ) : RecyclerView.ViewHolder(binding.root) {
        var listener: OnItemSelectedListener? = null

        fun bindMovie(movie: UpcomingMovieView?) {
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