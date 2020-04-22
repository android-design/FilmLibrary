package com.geekbrains.team.filmlibrary.fragments.mainScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
import com.geekbrains.team.filmlibrary.adapters.SmallCardAdapter
import com.geekbrains.team.filmlibrary.adapters.TopRatedBigCardAdapter
import com.geekbrains.team.filmlibrary.adapters.UpcomingSmallCardAdapter
import com.geekbrains.team.filmlibrary.databinding.MainScreenFragmentBinding
import com.geekbrains.team.filmlibrary.util.DiffUtilsCallback
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_screen_fragment.*
import javax.inject.Inject

class MainScreenFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainScreenViewModel> { viewModelFactory }
    lateinit var binding: MainScreenFragmentBinding
    private lateinit var listener: OnItemSelectedListener

    private val upcomingAdapter = UpcomingSmallCardAdapter()
    private val nowPlayingAdapter = SmallCardAdapter()
    private val topRatedMovieAdapter = TopRatedBigCardAdapter()

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
        binding = DataBindingUtil.inflate(inflater, R.layout.main_screen_fragment, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        attachListeners()
        startObservers()
        getInfoFromServer()
        showInfo()
    }

    private fun attachListeners() {
        nowPlayingAdapter.attachListener(listener)
        upcomingAdapter.attachListener(listener)
        topRatedMovieAdapter.attachListener(listener)
    }

    private fun startObservers() {
        viewModel.upcomingMoviesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                val diffUtilCallback = DiffUtilsCallback(upcomingAdapter.movie, it)
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                upcomingAdapter.setMovies(it.toMutableList())
                diffResult.dispatchUpdatesTo(upcomingAdapter)
            }
        })

        viewModel.nowPlayingMoviesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                val diffUtilCallback = DiffUtilsCallback(nowPlayingAdapter.movie, it)
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                nowPlayingAdapter.setMovies(it.toMutableList())
                diffResult.dispatchUpdatesTo(nowPlayingAdapter)
            }
        })

        viewModel.randomTopRatedMovieData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                topRatedMovieAdapter.setMovies(it)
                topRatedMovieAdapter.notifyDataSetChanged()
                startIndicators()
                setCurrentIndicator(0)
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
                    setCurrentIndicator(position)
                }
            })
        }
    }

    private fun startIndicators() {
        indicator.removeAllViews()
        val indicators = arrayOfNulls<ImageView>(topRatedMovieAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(context)
            indicators[i]?.setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.indicator_inactive
                    )
                }
            )
            indicators[i]?.layoutParams = layoutParams
            indicator.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount: Int = indicator.childCount
        for (i in 0 until childCount) {
            val imageView =
                indicator.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.indicator_active
                        )
                    }
                )
            } else {
                imageView.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.indicator_inactive
                        )
                    }
                )
            }
        }
    }
}