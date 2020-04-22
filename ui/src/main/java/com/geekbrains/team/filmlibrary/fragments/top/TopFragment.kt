package com.geekbrains.team.filmlibrary.fragments.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.MyTabAdapter
import kotlinx.android.synthetic.main.top_fragment.*

class TopFragment : Fragment() {

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

    override fun onResume() {
        super.onResume()
        showInfo()
    }

    private fun showInfo() {
        val mAdapter = fragmentManager?.let {
            MyTabAdapter(
                it,
                listOf(TopMovieFragment(), TopTVShowFragment()),
                arrayListOf(getString(R.string.movies), getString(R.string.tvShows))
            )

        }

        mAdapter?.let {
            viewPager.adapter = mAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }
}