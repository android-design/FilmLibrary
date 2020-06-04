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
import androidx.recyclerview.widget.DiffUtil
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
import javax.inject.Inject

class TopTVShowFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TopViewModel>({ activity as MainActivity }) { viewModelFactory }
    private lateinit var listener: OnItemSelectedListener

    private val tvShowsAdapter by lazy {
        ItemsAdapterNew(
            clickListener = listener,
            layout = R.layout.landscape_tv_show_card_item,
            comparator = ItemsAdapterNew.COMPARATOR_TVSHOW
        )
    }

    private val progressAdapter: ProgressAdapter by lazy {
        ProgressAdapter { viewModel.loadTopRatedTVShowMoore() }
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
    ): View? = inflater.inflate(R.layout.top_inner_fragment, container, false)

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
            adapter = MergeAdapter(tvShowsAdapter, progressAdapter)

            addOnScrollListenerPagination(manager) { viewModel.loadTopRatedTVShowMoore() }
        }
    }

    private fun startObservers() {
        viewModel.failure.observe(viewLifecycleOwner, Observer { error ->
            setProgressBarVisible(false)

            Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.topRatedTVShowsData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                setProgressBarVisible(false)

                tvShowsAdapter.submitList(it)
            }
        })

        viewModel.loadingTVShowState.observe(viewLifecycleOwner, Observer { loadState ->
            progressAdapter.loadState = loadState
        })
    }

    private fun setProgressBarVisible(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun getInfoFromServer() {
        if (viewModel.topRatedTVShowsData.value?.isEmpty() != false) {
            viewModel.loadTopRatedTVShows()
        }
    }
}