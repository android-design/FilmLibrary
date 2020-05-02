package com.geekbrains.team.filmlibrary.fragments.movieDetails

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.ItemsAdapter
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
import com.geekbrains.team.filmlibrary.adapters.OneItemAdapter
import com.geekbrains.team.filmlibrary.databinding.FullFilmInfoFragmentBinding
import com.geekbrains.team.filmlibrary.model.ActorView
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.util.DiffUtilsCallback
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.full_film_info_fragment.*
import kotlinx.android.synthetic.main.main_screen_fragment.indicator
import kotlinx.android.synthetic.main.main_screen_fragment.topPager
import kotlinx.android.synthetic.main.pager_indicator_item.*
import javax.inject.Inject

class FullFilmInfoFragment : DaggerFragment(), OnItemSelectedListener {
    private val args: FullFilmInfoFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FullFilmInfoViewModel>({ activity as MainActivity }) { viewModelFactory }
    lateinit var binding: FullFilmInfoFragmentBinding
    lateinit var listener: OnItemSelectedListener

    private val infoAdapter by lazy {
        OneItemAdapter<MovieView>(layout = R.layout.full_film_info_item)
    }

    private val actorsAdapter by lazy {
        ItemsAdapter<ActorView>(clickListener = listener, layout = R.layout.small_card_item)
    }

    private val similarMoviesAdapter by lazy {
        ItemsAdapter<MovieView>(clickListener = this, layout = R.layout.small_actor_card_item)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnItemSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnItemSelectedListener")
        }
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

        startObservers()
        loadMovieDetails()
        showMovieDetails()
    }

    private fun startObservers() {
        viewModel.failure.observe(viewLifecycleOwner, Observer { msg ->
            Toast.makeText(context, msg.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                infoAdapter.update(it)
                startIndicators()
                setCurrentIndicator(0)
                binding.movie = it
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
                Log.d("FullFilmInfoFragment", it[1].title)
                val diffUtilCallback = DiffUtilsCallback(similarMoviesAdapter.data, it)
                val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
                similarMoviesAdapter.update(it)
                diffResult.dispatchUpdatesTo(similarMoviesAdapter)
            }
        })
    }

    private fun loadMovieDetails() {
        viewModel.loadMovieInfo(args.id)
        viewModel.loadSimilarMovies(args.id, 1)
    }

    private fun showMovieDetails() {
        topPager.apply {
            adapter = infoAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position)
                }
            })
        }

        actors_rv.apply {
            adapter = actorsAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        similar_rv.apply {
            adapter = similarMoviesAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    private fun startIndicators() {
        indicator.removeAllViews()
        val indicators = arrayOfNulls<ImageView>(infoAdapter.itemCount)

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
            indicators[i]?.layoutParams = indicator_item.layoutParams
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

    override fun openMovieDetails(id: Int) {
        viewModel.loadMovieInfo(args.id)
    }
}