package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.movieDetails.FullFilmInfoFragment
import com.geekbrains.team.filmlibrary.fragments.movieDetails.FullFilmInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FullFilmInfoFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )

    internal abstract fun fullInfoFilmFragment(): FullFilmInfoFragment

    @Binds
    @IntoMap
    @ViewModelKey(FullFilmInfoViewModel::class)
    abstract fun bindViewModel(viewModel: FullFilmInfoViewModel): ViewModel
}