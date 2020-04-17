package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.searchMovies.repository.SearchMoviesRepositoryImpl
import com.geekbrains.team.domain.search.repository.SearchMoviesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SearchMoviesModule {
    @Binds
    @Singleton
    abstract fun provideRepositorySearchMovies(repository: SearchMoviesRepositoryImpl): SearchMoviesRepository
}