package com.geekbrains.team.filmlibrary.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoritesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FavoritesViewModel>({ activity as MainActivity }) { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

}
