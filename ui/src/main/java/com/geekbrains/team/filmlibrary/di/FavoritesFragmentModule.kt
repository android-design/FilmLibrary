package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.favorites.FavoritesFragment
import com.geekbrains.team.filmlibrary.fragments.favorites.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FavoritesFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun favoritesFragment(): FavoritesFragment

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindViewModel(viewModel: FavoritesViewModel): ViewModel
}