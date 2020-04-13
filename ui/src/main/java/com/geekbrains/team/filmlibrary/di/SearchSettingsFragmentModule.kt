package com.geekbrains.team.filmlibrary.di

import com.geekbrains.team.filmlibrary.fragments.search.SearchSettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchSettingsFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun searchSettingsFragment(): SearchSettingsFragment

//    @Binds
//    @IntoMap
//    @ViewModelKey(SearchViewModel::class)
//    abstract fun bindViewModel(viewModel: SearchViewModel): ViewModel
}