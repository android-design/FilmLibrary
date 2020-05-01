package com.geekbrains.team.filmlibrary.fragments.movieDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.Indicator
import com.geekbrains.team.filmlibrary.adapters.OneItemAdapter
import com.geekbrains.team.filmlibrary.databinding.FullFilmInfoFragmentBinding
import com.geekbrains.team.filmlibrary.model.MovieView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.full_film_info_fragment.*
import kotlinx.android.synthetic.main.main_screen_fragment.*
import kotlinx.android.synthetic.main.main_screen_fragment.indicator
import kotlinx.android.synthetic.main.main_screen_fragment.topPager
import kotlinx.android.synthetic.main.pager_indicator_item.*
import javax.inject.Inject

class FullFilmInfoFragment : DaggerFragment() {
    private val args: FullFilmInfoFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FullFilmInfoViewModel>({ activity as MainActivity }) { viewModelFactory }
    lateinit var binding: FullFilmInfoFragmentBinding
    private lateinit var mIndicator: Indicator
    private val infoAdapter by lazy {
        OneItemAdapter<MovieView>(layout = R.layout.full_film_info_item)
    }

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

        mIndicator = Indicator(context, indicator, indicator_item, infoAdapter)
        startObservers()
        showMovieDetails()
    }

    override fun onResume() {
        super.onResume()
        progress.visibility = VISIBLE
        scrollView.visibility = GONE
        infoAdapter.clear()
        loadMovieDetails()
    }

    private fun startObservers() {
        viewModel.failure.observe(viewLifecycleOwner, Observer { msg ->
            Toast.makeText(context, msg.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                infoAdapter.update(it)
                mIndicator.startIndicators()
                mIndicator.setCurrentIndicator(0)
                binding.movie = it
                progress.visibility = GONE
                scrollView.visibility = VISIBLE
            }
        })
    }

    private fun loadMovieDetails() {
        viewModel.loadMovieInfo(args.id)
    }

    private fun showMovieDetails() {
        topPager.apply {
            adapter = infoAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mIndicator.setCurrentIndicator(position)
                }
            })
        }
    }
}