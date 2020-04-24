package com.geekbrains.team.filmlibrary.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.databinding.*
import com.geekbrains.team.filmlibrary.fragments.mainScreen.model.UpcomingMovieView
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.big_card_item.view.*

object ViewHolderFactory {
    fun create(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.small_card_item -> SmallCardHolder(
                SmallCardItemBinding.inflate(inflater, parent, false)
            )
            R.layout.upcoming_small_card_item -> UpcomingSmallCardHolder(
                UpcomingSmallCardItemBinding.inflate(inflater, parent, false)
            )
            R.layout.landscape_card_item -> LandscapeCardHolder(
                LandscapeCardItemBinding.inflate(inflater, parent, false)
            )
            R.layout.landscape_tv_show_card_item -> LandscapeTVCardHolder(
                LandscapeTvShowCardItemBinding.inflate(inflater, parent, false)
            )
            R.layout.big_card_item -> TopRatedCardHolder(
                BigCardItemBinding.inflate(inflater, parent, false)
            )
            R.layout.full_film_info_item -> FullInfoCardHolder(
                FullFilmInfoItemBinding.inflate(inflater, parent, false)
            )
            else -> throw Exception("Wrong view type")
        }
    }

    class SmallCardHolder(
        private val binding: SmallCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root), GenericAdapter.Binder<MovieView> {
        override fun bind(data: MovieView, list: String?, listener: OnItemSelectedListener?) {
            binding.listener = listener
            binding.movie = data
            binding.executePendingBindings()
        }
    }

    class UpcomingSmallCardHolder(
        private val binding: UpcomingSmallCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root), GenericAdapter.Binder<UpcomingMovieView> {
        override fun bind(data: UpcomingMovieView, list: String?, listener: OnItemSelectedListener?) {
            binding.listener = listener
            binding.movie = data
            binding.executePendingBindings()

        }
    }

    class LandscapeCardHolder(
        private val binding: LandscapeCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root), GenericAdapter.Binder<MovieView> {
        override fun bind(data: MovieView, list: String?, listener: OnItemSelectedListener?) {
            binding.listener = listener
            binding.movie = data
            binding.executePendingBindings()

        }
    }

    class LandscapeTVCardHolder(
        private val binding: LandscapeTvShowCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root), GenericAdapter.Binder<TVShowView> {
        override fun bind(data: TVShowView, list: String?, listener: OnItemSelectedListener?) {
            binding.listener = listener
            binding.tvShow = data
            binding.executePendingBindings()

        }
    }

    class TopRatedCardHolder(
        private val binding: BigCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root), GenericAdapter.Binder<MovieView> {
        private val play: ImageButton = binding.root.play_btn
        private val backDrop: ImageView = binding.root.title_iv

        override fun bind(data: MovieView, list: String?, listener: OnItemSelectedListener?) {
            binding.listener = listener
            binding.movie = data
            binding.executePendingBindings()
            play.setOnClickListener { v: View ->
                v.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.trailer)))
            }
            Picasso.get().load(list).into(backDrop)
        }
    }

    class FullInfoCardHolder(
        private val binding: FullFilmInfoItemBinding
    ) : RecyclerView.ViewHolder(binding.root), GenericAdapter.Binder<MovieView> {
        private val play: ImageButton = binding.root.play_btn
        private val backDrop: ImageView = binding.root.title_iv

        override fun bind(data: MovieView, list: String?, listener: OnItemSelectedListener?) {
            binding.movie = data
            binding.executePendingBindings()
            play.setOnClickListener { v: View ->
                v.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.trailer)))
            }
            Picasso.get().load(list).into(backDrop)
        }
    }
}