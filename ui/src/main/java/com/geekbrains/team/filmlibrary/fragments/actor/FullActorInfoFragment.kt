package com.geekbrains.team.filmlibrary.fragments.actor

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.ImagesAdapter
import com.geekbrains.team.filmlibrary.databinding.FullActorInfoFragmentBinding
import com.geekbrains.team.filmlibrary.model.PersonView
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FullActorInfoFragment: DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FullActorInfoViewModel> {viewModelFactory}
    lateinit var binding: FullActorInfoFragmentBinding

    private val infoAdapter by lazy {
        ImagesAdapter<PersonView>(null, layout = R.layout.full_actor_info_item)
    }


}

/*
 private val args: FullFilmInfoFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<FullFilmInfoViewModel> { viewModelFactory }
    lateinit var binding: FullFilmInfoFragmentBinding
    private val onItemSelectedListener: OnItemSelectedListener = this

    private lateinit var mIndicator: Indicator
    private val infoAdapter by lazy {
        ImagesAdapter<MovieView>(clickListener = onItemSelectedListener,
            layout = R.layout.full_film_info_item)
    }
 */