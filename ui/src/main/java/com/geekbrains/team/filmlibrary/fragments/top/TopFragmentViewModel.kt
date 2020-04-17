package com.geekbrains.team.filmlibrary.fragments.top

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import javax.inject.Inject

class TopFragmentViewModel @Inject constructor(
) :
    BaseViewModel() {
    val fragments: MutableLiveData<List<Fragment>> = MutableLiveData()

    fun loadData() {
        fragments.value = listOf(TopMovieFragment(), TopTVShowFragment())
    }
}