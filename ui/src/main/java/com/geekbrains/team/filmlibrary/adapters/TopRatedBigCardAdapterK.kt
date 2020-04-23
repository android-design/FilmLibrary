package com.geekbrains.team.filmlibrary.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.databinding.BigCardItemBinding
import com.geekbrains.team.filmlibrary.model.MovieView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.big_card_item.view.*

class TopRatedBigCardAdapterK: RecyclerView.Adapter<TopRatedBigCardAdapterK.TopRatedCardHolder>() {
    var movie: MovieView? = null
    private var listener: OnItemSelectedListener? = null

    fun attachListener(listener: OnItemSelectedListener?) {
        this.listener = listener
    }

    fun setMovies(data: MovieView) {
        movie = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedCardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BigCardItemBinding.inflate(inflater, parent, false)
        return TopRatedCardHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return if (movie == null) 0
        else movie!!.images.size
    }

    override fun onBindViewHolder(holder: TopRatedCardHolder, position: Int) {
        if (movie != null) {
            holder.bindMovie(movie)
            holder.play.setOnClickListener { v: View ->
                v.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(movie!!.trailer)))
            }
            Picasso.get().load(movie!!.images[position]).into(holder.backDrop)
        }
    }

    class TopRatedCardHolder(
        private val binding: BigCardItemBinding,
        private val delegate: OnItemSelectedListener?
    ): RecyclerView.ViewHolder(binding.root) {
        val listener: OnItemSelectedListener? = null
        val play: ImageButton = binding.root.play_btn
        val backDrop: ImageView = binding.root.title_iv

        fun bindMovie(movie: MovieView?) {
            binding.listener = delegate
            binding.movie = movie
            binding.executePendingBindings()
        }
    }
}