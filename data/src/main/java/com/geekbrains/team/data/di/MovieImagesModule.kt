package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.movieImages.repository.MovieImagesRepositoryImpl
import com.geekbrains.team.domain.movies.commonRepository.MovieImagesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class MovieImagesModule {
    @Binds
    @Singleton
    abstract fun provideMovieImagesRepository(
        repository: MovieImagesRepositoryImpl): MovieImagesRepository
}