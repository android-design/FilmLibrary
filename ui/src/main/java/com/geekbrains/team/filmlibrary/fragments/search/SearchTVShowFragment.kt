package com.geekbrains.team.filmlibrary.fragments.search

import android.content.Context
import android.opengl.Visibility
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
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.ItemsAdapter
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
import com.geekbrains.team.filmlibrary.adapters.ProgressAdapter
import com.geekbrains.team.filmlibrary.model.TVShowView
import com.geekbrains.team.filmlibrary.util.DiffUtilsCallback
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_inner_fragment.*
import javax.inject.Inject

class SearchTVShowFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SearchViewModel>({ activity as MainActivity }) { viewModelFactory }
    private lateinit var listener: OnItemSelectedListener

    private val searchedTVAdapter: ItemsAdapter<TVShowView> by lazy(mode = LazyThreadSafetyMode.NONE) {
        ItemsAdapter<TVShowView>(
            clickListener = listener,
            layout = R.layout.landscape_search_item,
            onScrollToLastPageListener = {
                viewModel.loadSearchedTV(viewModel.currentQuery, ++viewModel.currentTVPage)
            }
        )
    }

    private val progressTVAdapter: ProgressAdapter by lazy {
        ProgressAdapter()
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
    ): View? {
        return inflater.inflate(R.layout.search_inner_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        startObservers()
    }

    private fun initViews() {
        with(inner_recycler) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = MergeAdapter(searchedTVAdapter, progressTVAdapter)
        }
    }

    private fun startObservers() {
        viewModel.failure.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.searchedTVData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                // TODO HIDE PB

//                searchedTVAdapter.isAllItemsLoaded = it.isEmpty()
//
//                if (searchedTVAdapter.isAllItemsLoaded) return@let
//
//                val oldList = searchedTVAdapter.data.toList()
//
//                if (viewModel.newTVPage) {
//                    searchedTVAdapter.addItems(it)
//                } else {
//                    searchedTVAdapter.update(it)
//                }
//
//                val diffUtilCallback = DiffUtilsCallback(oldList, searchedTVAdapter.data)
//                DiffUtil.calculateDiff(diffUtilCallback).dispatchUpdatesTo(searchedTVAdapter)
            }
        })

        viewModel.loadingTVState.observe(viewLifecycleOwner, Observer { state ->
//            if (state) {
//                progressBar.visibility = View.VISIBLE
////                progressTVAdapter.show()
////                progressTVAdapter.notifyItemInserted(1)
//            } else {
//                progressBar.visibility = View.INVISIBLE
////                progressTVAdapter.hide()
////                progressTVAdapter.notifyItemRemoved(1)
//            }
//
////            inner_recycler.post {
////                //progressTVAdapter.notifyDataSetChanged()
////                //notifyItemChanged(position)
////            }
////            progressTVAdapter.notifyDataSetChanged()
        })
    }
}