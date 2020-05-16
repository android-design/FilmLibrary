package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.search.*
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SearchFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun searchFragment(): SearchFragment

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun searchMovieFragment(): SearchMovieFragment

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun searchTVShowFragment(): SearchTVShowFragment

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindViewModel(viewModel: SearchViewModel): ViewModel
}