package com.geekbrains.team.filmlibrary.fragments.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.TabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.top_fragment.*

class TopFragment : Fragment() {

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
        return inflater.inflate(R.layout.top_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showInfo()
    }

    private fun showInfo() {
        val mAdapter = ((context as MainActivity).supportFragmentManager).run {
            TabAdapter(
                this,
                this@TopFragment.lifecycle,
                listOf(TopMovieFragment(), TopTVShowFragment())
            )
        }

        mAdapter.let {
            viewPager.adapter = mAdapter
        }

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = pageTitleList.getOrNull(position)
        }.attach()
    }
}