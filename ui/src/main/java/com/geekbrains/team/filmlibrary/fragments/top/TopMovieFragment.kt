package com.geekbrains.team.filmlibrary.fragments.top

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.ItemsAdapterNew
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
import com.geekbrains.team.filmlibrary.adapters.ProgressAdapter
import com.geekbrains.team.filmlibrary.addOnScrollListenerPagination
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.top_inner_fragment.*
import kotlinx.android.synthetic.main.top_inner_fragment.inner_recycler
import javax.inject.Inject

class TopMovieFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TopViewModel>({ activity as MainActivity }) { viewModelFactory }
    private lateinit var listener: OnItemSelectedListener

    private val moviesAdapter by lazy(mode = LazyThreadSafetyMode.NONE) {
        ItemsAdapterNew(
            clickListener = listener,
            layout = R.layout.landscape_card_item,
            comparator = ItemsAdapterNew.COMPARATOR_MOVIE
        )
    }

    private val progressAdapter: ProgressAdapter by lazy {
        ProgressAdapter { viewModel.loadTopRatedMoviesMoore() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnItemSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnItemSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.top_inner_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        startObservers()
        getInfoFromServer()
    }

    private fun initUI() {
        setProgressBarVisible(true)

        with(inner_recycler) {
            val manager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            layoutManager = manager
            adapter = MergeAdapter(moviesAdapter, progressAdapter)

            addOnScrollListenerPagination(manager) { viewModel.loadTopRatedMoviesMoore() }
        }
    }

    private fun startObservers() {

        viewModel.failure.observe(viewLifecycleOwner, Observer { error ->
            setProgressBarVisible(false)

            Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.topRatedMoviesData.observe(viewLifecycleOwner, Observer { data ->

            data?.let {
                setProgressBarVisible(false)

                moviesAdapter.submitList(it)

            }
        })

        viewModel.loadingMovieState.observe(viewLifecycleOwner, Observer { loadState ->
            progressAdapter.loadState = loadState
        })
    }

    private fun getInfoFromServer() {
        if (viewModel.topRatedMoviesData.value?.isEmpty() != false) {
            viewModel.loadTopRatedMovies()
        }
    }

    private fun setProgressBarVisible(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}