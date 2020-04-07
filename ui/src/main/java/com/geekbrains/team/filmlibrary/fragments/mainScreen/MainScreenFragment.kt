package com.geekbrains.team.filmlibrary.fragments.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.team.filmlibrary.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainScreenFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainScreenViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.upcomingMoviesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                //Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}