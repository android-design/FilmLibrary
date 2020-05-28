package com.geekbrains.team.filmlibrary.di

import androidx.lifecycle.ViewModel
import com.geekbrains.team.filmlibrary.fragments.actorDetails.FullActorInfoFragment
import com.geekbrains.team.filmlibrary.fragments.actorDetails.FullActorInfoViewModel
import com.geekbrains.team.filmlibrary.fragments.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FullActorInfoFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun fullActorInfoFragment(): FullActorInfoFragment

    @Binds
    @IntoMap
    @ViewModelKey(FullActorInfoViewModel::class)
    abstract fun bindViewModel(viewModel: FullActorInfoViewModel): ViewModel
}