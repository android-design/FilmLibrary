package com.geekbrains.team.filmlibrary.fragments.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.team.filmlibrary.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.search_fragment.*
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SearchViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.failure.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
        })
        viewModel.searchedMoviesData.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        search_et.setOnKeyListener { v, keyCode, event ->
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                viewModel.loadSearchedMovies(search_et.text.toString(), 1)
            }
            true
        }
    }
}