package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.tvDetails.FullSeriesInfoFragment
import com.geekbrains.team.filmlibrary.fragments.tvDetails.FullTvInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FullSeriesInfoFragmentModule {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )

    internal abstract fun movieDetailsFragment(): FullSeriesInfoFragment

    @Binds
    @IntoMap
    @ViewModelKey(FullTvInfoViewModel::class)
    abstract fun bindViewModel(viewModel: FullTvInfoViewModel): ViewModel
}