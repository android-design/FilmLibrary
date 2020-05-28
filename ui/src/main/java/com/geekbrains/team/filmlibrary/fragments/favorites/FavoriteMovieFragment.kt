package com.geekbrains.team.filmlibrary.fragments.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.ItemsAdapter
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.util.DiffUtilsCallback
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.favorite_inner_fragment.*
import java.lang.RuntimeException
import javax.inject.Inject

class FavoriteMovieFragment: DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FavoriteViewModel>({activity as MainActivity}) {viewModelFactory}
    private lateinit var listener: OnItemSelectedListener

    private val mAdapter: ItemsAdapter<MovieView, OnItemSelectedListener> by lazy {
        ItemsAdapter<MovieView, OnItemSelectedListener>(
            clickListener = listener,
            layout = R.layout.landscape_card_item
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is OnItemSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnItemSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_inner_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObservers()
        loadFavoriteMovies()
    }

    private fun startObservers() {
        viewModel.failure.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.favoriteMoviesLiveData.observe(viewLifecycleOwner, Observer {
            val diffUtilCallback = DiffUtilsCallback(mAdapter.data, it)
            val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
            mAdapter.update(it)
            diffResult.dispatchUpdatesTo(mAdapter)
            films_rv.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = mAdapter
            }
        })
    }

    private fun loadFavoriteMovies() {
        viewModel.loadFavoriteMovies()
    }
}