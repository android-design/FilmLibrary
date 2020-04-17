package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.favoriteMovies.FavoriteMoviesFragment
import com.geekbrains.team.filmlibrary.fragments.favoriteMovies.FavoriteMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteMoviesFragmentModule {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun favoriteMoviesFragment(): FavoriteMoviesFragment

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteMoviesViewModel::class)
    abstract fun bindViewModel(viewModel: FavoriteMoviesViewModel): ViewModel
}