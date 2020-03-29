package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.upcomingMovies.repository.UpcomingMoviesRepositoryImpl
import com.geekbrains.team.domain.movies.upcomingMovies.repository.UpcomingMoviesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UpcomingMoviesModule {
    @Binds
    @Singleton
    abstract fun provideRepositoryRemote(repository: UpcomingMoviesRepositoryImpl): UpcomingMoviesRepository
}