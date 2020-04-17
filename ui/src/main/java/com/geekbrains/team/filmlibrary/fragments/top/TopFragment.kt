package com.geekbrains.team.filmlibrary.fragments.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.team.filmlibrary.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TopFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TopViewModel> { viewModelFactory }

    //private val mAdapter = SearchAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.failure.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.topRatedMoviesData.observe(viewLifecycleOwner, Observer { data ->
            Toast.makeText(context, data.toString(), Toast.LENGTH_LONG).show()
        })

        viewModel.topRatedTVShowsData.observe(viewLifecycleOwner, Observer { data ->
            Toast.makeText(context, data.toString(), Toast.LENGTH_LONG).show()
        })

        viewModel.loadTopRatedMovies()
        viewModel.loadTopRatedTVShows()

//        viewModel.searchedMoviesData.observe(viewLifecycleOwner, Observer { data ->
//            data?.let {
//                val diffUtilCallback = DiffUtilsCallback(mAdapter.mDataList, it)
//                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
//                mAdapter.setData(it)
//                diffResult.dispatchUpdatesTo(mAdapter)
//            }
//        })

//        search_rv.apply {
//            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//            adapter = mAdapter
//        }
    }
}