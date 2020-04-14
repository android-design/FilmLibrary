package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.topRatedMovies.repository.TopRatedMoviesRepositoryImpl
import com.geekbrains.team.domain.movies.topRatedMovies.repository.TopRatedMoviesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class TopRatedMoviesModule {
    @Binds
    @Singleton
    abstract fun provideRepositoryTopRatedMovies(repository: TopRatedMoviesRepositoryImpl): TopRatedMoviesRepository
}