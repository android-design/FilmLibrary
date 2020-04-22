package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.detailsMovies.repository.MovieDetailsRepositoryImpl
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class MovieDetailsModule {
    @Binds
    @Singleton
    abstract fun provideMovieDetailsRepository(repository: MovieDetailsRepositoryImpl): MovieDetailsRepository
}