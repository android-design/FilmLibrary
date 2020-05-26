package com.geekbrains.team.filmlibrary.fragments.movieDetails

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.*
import com.geekbrains.team.filmlibrary.databinding.FullFilmInfoFragmentBinding
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.PersonView
import com.geekbrains.team.filmlibrary.util.DiffUtilsCallback
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.full_film_info_fragment.*
import kotlinx.android.synthetic.main.main_screen_fragment.indicator
import kotlinx.android.synthetic.main.main_screen_fragment.topPager
import kotlinx.android.synthetic.main.pager_indicator_item.*
import javax.inject.Inject

class FullFilmInfoFragment : DaggerFragment(), OnLikeClickListener {
    private val args: FullFilmInfoFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FullFilmInfoViewModel> { viewModelFactory }
    lateinit var binding: FullFilmInfoFragmentBinding
    private val onLikeClickListener: OnLikeClickListener = this
    private lateinit var onActorSelectedListener: OnActorSelectedListener
    private lateinit var onItemSelectedListener: OnItemSelectedListener


    private lateinit var mIndicator: Indicator<MovieView, OnLikeClickListener>
    private val infoAdapter by lazy {
        ImagesAdapter<MovieView, OnLikeClickListener>(clickListener = onLikeClickListener,
            layout = R.layout.full_film_info_item)
    }

    private val actorsAdapter by lazy {
        ItemsAdapter<PersonView, OnActorSelectedListener>(clickListener = onActorSelectedListener,
            layout = R.layout.small_actor_card_item)
    }

    private val similarMoviesAdapter by lazy {
        ItemsAdapter<MovieView, OnItemSelectedListener>(clickListener = onItemSelectedListener,
            layout = R.layout.small_card_item)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnActorSelectedListener) {
            onActorSelectedListener = context
        } else {
            throw RuntimeException("$context must implement OnItemSelectedListener")
        }

        if (context is OnItemSelectedListener) {
            onItemSelectedListener = context
        } else {
            throw RuntimeException("$context must implement OnItemSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.full_film_info_fragment,
            null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mIndicator = Indicator(context, indicator, indicator_item, infoAdapter)
        startObservers()
        loadMovieDetails()
        showMovieDetails()
    }

    override fun onResume() {
        super.onResume()
        showProgressBar()
        infoAdapter.clear()
        loadMovieDetails()
    }

    private fun showProgressBar() {
        progress.visibility = VISIBLE
        scrollView.visibility = GONE
    }

    private fun startObservers() {
        viewModel.failure.observe(viewLifecycleOwner, Observer { msg ->
            Toast.makeText(context, msg.localizedMessage, Toast.LENGTH_LONG).show()
            hideProgressBar()
        })

        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                infoAdapter.data = it
                mIndicator.startIndicators()
                mIndicator.setCurrentIndicator(0)
                binding.movie = it
                hideProgressBar()
            }
        })

        viewModel.actorsLiveData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                val diffUtilCallback = DiffUtilsCallback(actorsAdapter.data, it)
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                actorsAdapter.update(it)
                diffResult.dispatchUpdatesTo(actorsAdapter)
            }
        })

        viewModel.similarMoviesLiveData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                val diffUtilCallback = DiffUtilsCallback(similarMoviesAdapter.data, it)
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                similarMoviesAdapter.update(it)
                diffResult.dispatchUpdatesTo(similarMoviesAdapter)
            }
        })
    }

    private fun hideProgressBar() {
        progress.visibility = GONE
        scrollView.visibility = VISIBLE
    }

    private fun loadMovieDetails() {
        viewModel.loadMovieInfo(args.id)
        viewModel.loadSimilarMovies(args.id)
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

        actors_rv.apply {
            adapter = actorsAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,
                false)
        }

        similar_rv.apply {
            adapter = similarMoviesAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,
                false)
        }
    }

    override fun onLikeClick(id: Int) {
        viewModel.addInFavorite(id)
    }
}