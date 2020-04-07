package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.searchMovies.repository.SearchTVRepositoryImpl
import com.geekbrains.team.domain.tv.searchTVShow.repository.SearchTVShowRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SearchTVShowModule {
    @Binds
    @Singleton
    abstract fun provideRepositorySearchTVShow(repository: SearchTVRepositoryImpl): SearchTVShowRepository
}