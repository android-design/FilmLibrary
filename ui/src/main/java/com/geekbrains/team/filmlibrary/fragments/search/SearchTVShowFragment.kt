package com.geekbrains.team.filmlibrary.fragments.search

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
import com.geekbrains.team.filmlibrary.adapters.ItemsAdapterSearch
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
import com.geekbrains.team.filmlibrary.adapters.ProgressAdapter
import com.geekbrains.team.filmlibrary.model.TVShowView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.search_inner_fragment.*
import javax.inject.Inject

class SearchTVShowFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SearchViewModel>({ activity as MainActivity }) { viewModelFactory }
    private lateinit var listener: OnItemSelectedListener

    private val searchedTVAdapter: ItemsAdapterSearch<TVShowView> by lazy(mode = LazyThreadSafetyMode.NONE) {
        ItemsAdapterSearch(
            clickListener = listener,
            layout = R.layout.landscape_search_item,
            comparator = ItemsAdapterSearch.COMPARATOR_TVSHOW
        )
    }

    private val progressAdapter: ProgressAdapter by lazy {
        ProgressAdapter { viewModel.loadSearchedTVMoore() }
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
    ): View? = inflater.inflate(R.layout.search_inner_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        startObservers()
    }

    private fun initViews() {
        with(inner_recycler) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = MergeAdapter(searchedTVAdapter, progressAdapter)
        }
    }

    private fun startObservers() {
        viewModel.failure.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.searchedTVData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                if (viewModel.isFirstTVShowPage) {
                    searchedTVAdapter.submitList(data)
                    inner_recycler.scrollToPosition(0)
                    (parentFragment as? SearchFragment)?.setupScrollListener(inner_recycler) { viewModel.loadSearchedTVMoore() }
                } else {
                    val newList = listOf(searchedTVAdapter.currentList, data).flatten()

                    searchedTVAdapter.submitList(newList)
                }
            }
        })

        viewModel.loadingTVState.observe(viewLifecycleOwner, Observer { loadState ->
            progressAdapter.loadState = loadState
        })
    }
}