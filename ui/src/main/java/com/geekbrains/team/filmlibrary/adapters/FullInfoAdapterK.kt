package com.geekbrains.team.filmlibrary.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.databinding.FullFilmInfoItemBinding
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.full_film_info_item.view.*

class FullInfoAdapterK : RecyclerView.Adapter<FullInfoAdapterK.FullInfoHolder>() {
    private var movie: MovieView? = null
    private var tvShow: TVShowView? = null

    fun setMovie(data: MovieView) {
        movie = data
    }

    fun setTvShow(data: TVShowView) {
        tvShow = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FullInfoHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FullFilmInfoItemBinding.inflate(inflater, parent, false)
        return FullInfoHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (movie == null && tvShow == null) 0
        else {
            if (movie != null) movie!!.images.size else tvShow!!.images.size
        }
    }

    override fun onBindViewHolder(holder: FullInfoHolder, position: Int) {
        val trailer: String
        val backDrop: String

        if (movie != null) {
            holder.bindMovie(movie)
            trailer = movie!!.trailer
            backDrop = movie!!.images[position]
        } else {
            holder.bindTVShow(tvShow)
            trailer = tvShow!!.trailer
            backDrop = tvShow!!.images[position]
        }

        holder.play.setOnClickListener { v: View ->
            v.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(trailer)))
        }

        Picasso.get().load(backDrop).into(holder.backDrop)
    }

    class FullInfoHolder(
        private val binding: FullFilmInfoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val play: ImageButton = binding.root.play_btn
        val backDrop: ImageView = binding.root.title_iv


        fun bindMovie(movie: MovieView?) {
            binding.movie = movie
            binding.executePendingBindings()
        }

        fun bindTVShow(movie: TVShowView?) {
            binding.tvShow = movie
            binding.executePendingBindings()
        }
    }
}