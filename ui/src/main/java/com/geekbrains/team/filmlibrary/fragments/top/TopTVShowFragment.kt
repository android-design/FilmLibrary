package com.geekbrains.team.filmlibrary.fragments.top

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
import com.geekbrains.team.filmlibrary.adapters.LandscapeCardAdapter
import com.geekbrains.team.filmlibrary.util.DiffUtilsCallback
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.top_inner_fragment.*
import javax.inject.Inject

class TopTVShowFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TopTVShowViewModel>({ activity as MainActivity }) { viewModelFactory }

    private val tvShowsAdapter = LandscapeCardAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_inner_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.failure.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.topRatedMoviesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                val diffUtilCallback = DiffUtilsCallback(tvShowsAdapter.tvShow, it)
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                tvShowsAdapter.setMovies(it)
                diffResult.dispatchUpdatesTo(tvShowsAdapter)
                inner_top_recycler.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    adapter = tvShowsAdapter
                }
            }
        })

        viewModel.loadTopRatedMovies(1)
    }
}