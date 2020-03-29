package com.geekbrains.team.filmlibrary.movies.upcomingMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.movies.upcomingMovies.viewModel.UpcomingMoviesViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.test_fragment.*
import javax.inject.Inject

class TestUpcomingMoviesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<UpcomingMoviesViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        test_button.setOnClickListener {
            viewModel.loadUpcomingMovies(1)
        }
    }
}
