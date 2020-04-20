package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.searchMovies.repository.SearchTVRepositoryImpl
import com.geekbrains.team.domain.search.repository.SearchTVRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SearchTVModule {
    @Binds
    @Singleton
    abstract fun provideRepositorySearchTVShow(repository: SearchTVRepositoryImpl): SearchTVRepository
}