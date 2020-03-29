package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.movies.upcomingMovies.TestUpcomingMoviesFragment
import com.geekbrains.team.filmlibrary.movies.upcomingMovies.viewModel.UpcomingMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ExampleFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun preferenceFragment(): TestUpcomingMoviesFragment

    @Binds
    @IntoMap
    @ViewModelKey(UpcomingMoviesViewModel::class)
    abstract fun bindViewModel(viewModel: UpcomingMoviesViewModel): ViewModel
}