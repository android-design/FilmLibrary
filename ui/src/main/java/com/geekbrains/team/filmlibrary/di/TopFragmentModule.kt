package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.top.TopFragment
import com.geekbrains.team.filmlibrary.fragments.top.TopViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TopFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun topFragment(): TopFragment

    @Binds
    @IntoMap
    @ViewModelKey(TopViewModel::class)
    abstract fun bindViewModel(viewModel: TopViewModel): ViewModel
}