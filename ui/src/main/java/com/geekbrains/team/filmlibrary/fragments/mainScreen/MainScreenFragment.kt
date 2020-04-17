package com.geekbrains.team.filmlibrary.fragments.mainScreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import com.geekbrains.team.filmlibrary.adapters.BigCardAdapter
import com.geekbrains.team.filmlibrary.adapters.SmallCardAdapter
import com.geekbrains.team.filmlibrary.adapters.UpcomingSmallCardAdapter
import com.geekbrains.team.filmlibrary.databinding.MainScreenFragmentBinding
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView
import com.geekbrains.team.filmlibrary.util.DiffUtilsCallback
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_screen_fragment.*
import javax.inject.Inject

class MainScreenFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainScreenViewModel> { viewModelFactory }
    lateinit var binding: MainScreenFragmentBinding

    private val upcomingAdapter = UpcomingSmallCardAdapter()
    private val nowPlayingAdapter = SmallCardAdapter()
    private val topRatedMovieAdapter = BigCardAdapter()

    private val defaultMovie = MovieView(
        1, "", "", "", "", listOf(
            "https://image.tmdb.org/t/p/w500/3lu6iHT189M6SL8q9OSmISYDoop.jpg",
            "https://image.tmdb.org/t/p/w500/4VGR3bzjfVQ0skc8T1O92ieyKLa.jpg",
            "https://image.tmdb.org/t/p/w500/oZibj2AItah70g4CzFgOw3jiFln.jpg",
            "https://image.tmdb.org/t/p/w500/elvVHhtKYFLoGGhfyKhhA0wQ4kc.jpg",
            "https://image.tmdb.org/t/p/w500/axqGyWPzkN8WNdl6wGwOd3EdRKE.jpg"
        ), "https://www.youtube.com/watch?v=P6AaSMfXHbA", "", "", "", "", "", ""
    )
    private val defaultTvShow =
        TVShowView(
            1, "Ad Astra", "К Звёздам", "", "8.5", "", listOf(
                "https://image.tmdb.org/t/p/w500/3lu6iHT189M6SL8q9OSmISYDoop.jpg",
                "https://image.tmdb.org/t/p/w500/4VGR3bzjfVQ0skc8T1O92ieyKLa.jpg",
                "https://image.tmdb.org/t/p/w500/oZibj2AItah70g4CzFgOw3jiFln.jpg",
                "https://image.tmdb.org/t/p/w500/elvVHhtKYFLoGGhfyKhhA0wQ4kc.jpg",
                "https://image.tmdb.org/t/p/w500/axqGyWPzkN8WNdl6wGwOd3EdRKE.jpg"
            ), "https://www.youtube.com/watch?v=P6AaSMfXHbA", "https://image.tmdb.org/t/p/w500/axqGyWPzkN8WNdl6wGwOd3EdRKE.jpg", "", "", "", 1, "", listOf(""), ""
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_screen_fragment, null, false)
        binding.movieOfTheWeek = defaultMovie
        binding.tvShowPremier = defaultTvShow
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startObservers()

        getInfoFromServer()

        showInfo()
    }

    private fun startObservers() {
        viewModel.upcomingMoviesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                val diffUtilCallback = DiffUtilsCallback(upcomingAdapter.movie, it)
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                upcomingAdapter.setMovies(it)
                diffResult.dispatchUpdatesTo(upcomingAdapter)
            }
        })

        viewModel.nowPlayingMoviesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                val diffUtilCallback = DiffUtilsCallback(nowPlayingAdapter.movie, it)
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                nowPlayingAdapter.setMovies(it)
                diffResult.dispatchUpdatesTo(nowPlayingAdapter)
            }
        })

        viewModel.topRatedMoviesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                Log.d("ANSWER", it.toString())
                val diffUtilCallback = DiffUtilsCallback(topRatedMovieAdapter.movie, it)
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                topRatedMovieAdapter.setMovies(it.take(5))
                startIndicators()
                setCurrentIndicator(0)
                diffResult.dispatchUpdatesTo(topRatedMovieAdapter)
            }
        })

        viewModel.movieOfTheWeek.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                binding.movieOfTheWeek = it
                film_of_week_play_btn.setOnClickListener {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(data.trailer)
                        )
                    )
                }
            }
        })

        viewModel.tvShowPremier.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
//                binding.tvShowPremier = it
                series_play_btn.setOnClickListener {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(defaultTvShow.trailer)
//                                    Uri.parse(data.trailer)
                        )
                    )
                }
            }
        })
    }

    private fun getInfoFromServer() {
        viewModel.loadNowPlayingMovies(1)
        viewModel.loadUpcomingMovies(1)
        viewModel.loadTopRatedMovies()
        viewModel.loadMovieOfTheWeek(1)
        viewModel.loadTvShowPremier(1)
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
            indicators[i]!!.setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.indicator_inactive
                    )
                }
            )
            indicators[i]!!.layoutParams = layoutParams
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