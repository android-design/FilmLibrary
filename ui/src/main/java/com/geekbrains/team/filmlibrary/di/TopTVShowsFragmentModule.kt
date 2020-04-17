package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.top.TopTVShowFragment
import com.geekbrains.team.filmlibrary.fragments.top.TopTVShowViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TopTVShowsFragmentModule {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun topMovieFragment(): TopTVShowFragment

    @Binds
    @IntoMap
    @ViewModelKey(TopTVShowViewModel::class)
    abstract fun bindViewModel(viewModel: TopTVShowViewModel): ViewModel
}