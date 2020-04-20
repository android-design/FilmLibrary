package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.movieDetails.MovieDetailsFragment
import com.geekbrains.team.filmlibrary.fragments.movieDetails.MovieDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MovieDetailsFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )

    internal abstract fun movieDetailsFragment(): MovieDetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindViewModel(viewModel: MovieDetailsViewModel): ViewModel
}