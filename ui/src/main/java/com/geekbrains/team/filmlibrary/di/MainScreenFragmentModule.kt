package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.mainScreen.MainScreenFragment
import com.geekbrains.team.filmlibrary.fragments.mainScreen.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainScreenFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )

    internal abstract fun mainScreenFragment(): MainScreenFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun bindViewModel(viewModel: MainScreenViewModel): ViewModel

}