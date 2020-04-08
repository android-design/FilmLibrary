package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.databinding.BigCardItemBinding
import com.geekbrains.team.filmlibrary.fragments.mainScreen.model.NowPlayingMovieView
import com.geekbrains.team.filmlibrary.fragments.mainScreen.model.UpcomingMovieView
import com.squareup.picasso.Picasso
import java.util.*

internal class MovieBigCardAdapterKotlin : RecyclerView.Adapter<MovieBigCardAdapterKotlin.BigCardHolder>() {
    private val nowPlayingMovie: MutableList<NowPlayingMovieView> = ArrayList()
    private val upcomingMovie: MutableList<UpcomingMovieView> = ArrayList()

    fun setNowPlayingMovie(data: List<NowPlayingMovieView>?) {
        nowPlayingMovie.clear()
        nowPlayingMovie.addAll(data!!)
    }

    fun setUpcomingMovie(data: List<UpcomingMovieView>?) {
        upcomingMovie.clear()
        upcomingMovie.addAll(data!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BigCardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: BigCardItemBinding = DataBindingUtil.inflate(inflater, R.layout.big_card_item, parent, false)
        return BigCardHolder(binding)
    }

    override fun onBindViewHolder(holder: BigCardHolder, position: Int) {
        if (nowPlayingMovie.isNotEmpty()) holder.bindNowPlayingMovie(nowPlayingMovie[position]) else holder.bindUpcomingMovie(
            upcomingMovie[position]
        )
    }

    override fun getItemCount(): Int {
        return if (nowPlayingMovie.isNotEmpty()) nowPlayingMovie.size else upcomingMovie.size
    }

    internal inner class BigCardHolder(var binding: BigCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindNowPlayingMovie(movie: NowPlayingMovieView?) {
            binding.nowPlayingMovie = movie
            binding.executePendingBindings()
        }

        fun bindUpcomingMovie(movie: UpcomingMovieView?) {
            binding.upcomingMovie = movie
            binding.executePendingBindings()
        }
    }

    companion object {
        @BindingAdapter("app:url")
        fun loadImage(view: ImageView?, url: String?) {
            Picasso.get().load(url).into(view)
        }
    }
}