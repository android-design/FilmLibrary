package com.geekbrains.team.filmlibrary.fragments.favoriteMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.favorite_inner_fragment.*
import javax.inject.Inject

class FavoriteMoviesFragment: DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val viewModel by viewModels<FavoriteMoviesViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FavoriteMoviesRVAdapter()
        val filmsRv = view.findViewById<View>(R.id.films_rv) as? RecyclerView
        filmsRv?.layoutManager = GridLayoutManager(this.context, 2)
        filmsRv?.adapter = adapter
        viewModel.loadFavoriteMovies()
        viewModel.liveData.observe(viewLifecycleOwner, Observer { it ->
            adapter.movies = it })
    }
}