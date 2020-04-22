package com.geekbrains.team.filmlibrary.fragments.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.FullInfoAdapter
import com.geekbrains.team.filmlibrary.databinding.FullFilmInfoFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieDetailsFragment : DaggerFragment() {
    private val args: MovieDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MovieDetailsViewModel>({ activity as MainActivity }) { viewModelFactory }
    lateinit var binding: FullFilmInfoFragmentBinding
    private val adapter = FullInfoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.full_film_info_fragment, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.failure.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                adapter.setFullMovie(it)

            }
        })
    }
}

/*
class TopFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TopViewModel>({ activity as MainActivity }) { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_fragment, container, false)
    }

}
 */