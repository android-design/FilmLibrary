package com.geekbrains.team.filmlibrary.fragments.top

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
import com.geekbrains.team.filmlibrary.adapters.TabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.top_fragment.*

class TopFragment : Fragment() {

    private lateinit var listener: OnItemSelectedListener

    private val pageTitleList by lazy {
        arrayListOf(
            getString(R.string.movies),
            getString(R.string.tvShows)
        )
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
    ): View? = inflater.inflate(R.layout.top_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showInfo()
    }

    private fun showInfo() {
        viewPager.adapter = childFragmentManager.run {
            TabAdapter(
                this,
                this@TopFragment.lifecycle,
                listOf(TopMovieFragment(), TopTVShowFragment())
            )
        }

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = pageTitleList.getOrNull(position)
        }.attach()
    }
}