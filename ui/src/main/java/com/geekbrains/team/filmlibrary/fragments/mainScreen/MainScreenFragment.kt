package com.geekbrains.team.filmlibrary.fragments.mainScreen

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.ImagesAdapter
import com.geekbrains.team.filmlibrary.adapters.Indicator
import com.geekbrains.team.filmlibrary.adapters.ItemsAdapter
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
import com.geekbrains.team.filmlibrary.databinding.MainScreenFragmentBinding
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.util.DiffUtilsCallback
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_screen_fragment.*
import kotlinx.android.synthetic.main.pager_indicator_item.*
import javax.inject.Inject


class MainScreenFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainScreenViewModel> { viewModelFactory }
    private lateinit var binding: MainScreenFragmentBinding
    private lateinit var listener: OnItemSelectedListener
    private lateinit var mIndicator: Indicator

    private val nowPlayingAdapter by lazy {
        ItemsAdapter<MovieView>(clickListener = listener, layout = R.layout.small_card_item)
    }

    private val upcomingAdapter by lazy {
        ItemsAdapter<MovieView>(
            clickListener = listener,
            layout = R.layout.upcoming_small_card_item
        )
    }

    private val topRatedMovieAdapter by lazy {
        ImagesAdapter<MovieView>(
            clickListener = listener,
            layout = R.layout.big_card_item
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
    ): View? {
        listener.showProgress()
        binding = DataBindingUtil.inflate(inflater, R.layout.main_screen_fragment, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mIndicator = Indicator(context, indicator, indicator_item, topRatedMovieAdapter)
        startObservers()
        getInfoFromServer()
        showInfo()
    }

    private fun startObservers() {
        viewModel.failure.observe(viewLifecycleOwner, Observer { msg ->
            Toast.makeText(context, msg.localizedMessage, Toast.LENGTH_LONG).show()
            hideProgressBar()
        })

        viewModel.upcomingMoviesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                val diffUtilCallback = DiffUtilsCallback(upcomingAdapter.data, it)
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                upcomingAdapter.update(it)
                diffResult.dispatchUpdatesTo(upcomingAdapter)
            }
        })

        viewModel.nowPlayingMoviesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                val diffUtilCallback = DiffUtilsCallback(nowPlayingAdapter.data, it)
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                nowPlayingAdapter.update(it)
                diffResult.dispatchUpdatesTo(nowPlayingAdapter)
            }
        })

        viewModel.randomTopRatedMovieData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                topRatedMovieAdapter.data = it
                topPager.currentItem = 0
                mIndicator.startIndicators()
                mIndicator.setCurrentIndicator(0)
                hideProgressBar()
            }
        })

        viewModel.movieOfTheWeekData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                binding.movieOfTheWeek = it
                binding.listener = listener
                film_of_week_play_btn.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.trailer)))
                }
            }
        })

        viewModel.tvShowPremierData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                binding.tvShowPremier = it
                binding.listener = listener
                series_play_btn.setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.trailer)))
                }
            }
        })
    }

    private fun hideProgressBar() {
        listener.hideProgress()
    }

    private fun getInfoFromServer() {
        viewModel.loadNowPlayingMovies()
        viewModel.loadUpcomingMovies()
        viewModel.loadRandomTopRatedMovie()
        viewModel.loadMovieOfTheWeek()
        viewModel.loadTvShowPremier()
    }

    private fun showInfo() {
        today_recycler.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = nowPlayingAdapter
        }

        soon_recycler.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = upcomingAdapter
        }

        topPager.apply {
            adapter = topRatedMovieAdapter
            registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mIndicator.setCurrentIndicator(position)
                }
            })
        }
    }
}