package com.geekbrains.team.filmlibrary.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.search_settings_fragment.*
import javax.inject.Inject

class SearchSettingsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SearchViewModel>({ activity as MainActivity }) { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        viewModel.isNeedSearchMovies = true
                        viewModel.isNeedSearchTVShows = true
                    }
                    1 -> {
                        viewModel.isNeedSearchMovies = true
                        viewModel.isNeedSearchTVShows = false
                    }
                    2 -> {
                        viewModel.isNeedSearchMovies = false
                        viewModel.isNeedSearchTVShows = true
                    }
                }
            }
        })

        when {
            viewModel.isNeedSearchMovies && viewModel.isNeedSearchTVShows -> tabs.getTabAt(0)
                ?.select()
            viewModel.isNeedSearchMovies -> tabs.getTabAt(1)?.select()
            else -> tabs.getTabAt(2)?.select()
        }
    }
}