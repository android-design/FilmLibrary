package com.geekbrains.team.filmlibrary.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.fragments.favorites.adapter.FavoriteAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.favorite_inner_fragment.*
import javax.inject.Inject

class FavoriteFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FavoriteViewModel> { viewModelFactory }

    private val mAdapter = FavoriteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        films_rv?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mAdapter
        }

        viewModel.favoriteMoviesData.observe(viewLifecycleOwner, Observer { movies ->
            mAdapter.movies = movies
        })

        viewModel.loadFavoriteMovies()
    }
}