package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.top.TopMovieFragment
import com.geekbrains.team.filmlibrary.fragments.top.TopMovieViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TopMovieFragmentModule {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun topMovieFragment(): TopMovieFragment

    @Binds
    @IntoMap
    @ViewModelKey(TopMovieViewModel::class)
    abstract fun bindViewModel(viewModel: TopMovieViewModel): ViewModel
}