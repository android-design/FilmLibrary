package com.geekbrains.team.filmlibrary.fragments.top

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.MainActivity
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.ItemsAdapter
import com.geekbrains.team.filmlibrary.adapters.ItemsAdapterNew
import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.util.DiffUtilsCallback
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.top_inner_fragment.*
import javax.inject.Inject

class TopMovieFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TopViewModel>({ activity as MainActivity }) { viewModelFactory }
    private lateinit var listener: OnItemSelectedListener

    private val moviesAdapter by lazy {
        ItemsAdapterNew(
            clickListener = listener,
            layout = R.layout.landscape_card_item,
            comparator = ItemsAdapterNew.COMPARATOR_MOVIE
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
    ): View? = inflater.inflate(R.layout.top_inner_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        startObservers()
        getInfoFromServer()
    }

    private fun initUI() {
        listener.showProgress()

        with(inner_recycler) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = moviesAdapter
        }
    }

    private fun startObservers() {
        viewModel.failure.observe(viewLifecycleOwner, Observer { error ->
            listener.hideProgress()

            Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
        })

        viewModel.topRatedMoviesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                listener.hideProgress()

                moviesAdapter.submitList(it)
            }
        })
    }

    private fun getInfoFromServer() {
        viewModel.loadTopRatedMovies()
    }
}