package com.geekbrains.team.filmlibrary.fragments.favoriteMovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.filmlibrary.R
import kotlinx.android.synthetic.main.small_card_item.view.*

class FavoriteMoviesRVAdapter : RecyclerView.Adapter<FavoriteMoviesRVAdapter.ViewHolder>() {

    var movies: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.small_card_item, parent, false))

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(movies[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) = with(itemView) {
            title_tv.text = movie.title

        }
    }
}