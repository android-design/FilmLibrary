package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.detailsMovies.repository.MovieDetailsRepositoryImpl
import com.geekbrains.team.domain.movies.detailsMovies.repository.MovieDetailsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class MovieDetailsModule {
    @Binds
    @Singleton
    abstract fun provideRepositoryFavoriteMovies(repository: MovieDetailsRepositoryImpl): MovieDetailsRepository
}