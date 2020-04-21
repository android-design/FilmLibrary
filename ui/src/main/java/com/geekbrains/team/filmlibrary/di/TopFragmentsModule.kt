package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.top.TopMovieFragment
import com.geekbrains.team.filmlibrary.fragments.top.TopTVShowFragment
import com.geekbrains.team.filmlibrary.fragments.top.TopViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TopFragmentsModule {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun topMovieFragment(): TopMovieFragment

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun topTVShowsFragment(): TopTVShowFragment

    @Binds
    @IntoMap
    @ViewModelKey(TopViewModel::class)
    abstract fun bindViewModel(viewModel: TopViewModel): ViewModel
}