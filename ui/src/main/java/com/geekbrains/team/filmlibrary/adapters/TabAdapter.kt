package com.geekbrains.team.filmlibrary.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabAdapter(
    fm: FragmentManager,
    lifeCycle: Lifecycle,
    val fragments: List<Fragment>
) : FragmentStateAdapter(fm, lifeCycle) {

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}