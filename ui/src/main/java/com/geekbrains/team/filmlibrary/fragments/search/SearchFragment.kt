package com.geekbrains.team.filmlibrary.fragments.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.TabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.search_fragment.*
import kotlinx.android.synthetic.main.search_inner_fragment.*
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SearchViewModel>({ activity as MainActivity }) { viewModelFactory }

    private val pageTitleList by lazy {
        arrayListOf(
            getString(R.string.movies),
            getString(R.string.tvShows)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        showInfo()
    }

    private fun initUI() {
        search_et.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                // TODO Show PB and hide keyboard

                search_et.text.toString().let {
                    viewModel.currentQuery = it
                    viewModel.currentMoviePage = 1
                    viewModel.loadSearchedMovies(it)
                    viewModel.currentTVPage = 1
                    viewModel.loadSearchedTV(it)
                }
            }
            true
        }
    }

    private fun showInfo() {

        viewPager.adapter = childFragmentManager.run {
            TabAdapter(
                this,
                this@SearchFragment.lifecycle,
                listOf(SearchMovieFragment(), SearchTVShowFragment())
            )
        }

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = pageTitleList.getOrNull(position)
        }.attach()
    }

    fun setupScrollListener() {
        val layoutManager = inner_recycler.layoutManager as? LinearLayoutManager
        layoutManager?.let {
            with(inner_recycler) {
                clearOnScrollListeners()

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val totalItemCount = layoutManager.itemCount
                        val visibleItemCount = layoutManager.childCount
                        val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                        viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
                    }
                })
            }
        }
    }
}