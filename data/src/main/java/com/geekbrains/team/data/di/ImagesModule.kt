package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.imagesMovies.repository.MoviesImagesRepositoryImpl
import com.geekbrains.team.domain.movies.commonRepository.MoviesImagesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ImagesModule {
    @Binds
    @Singleton
    abstract fun provideRepositoryMoviesImages(repository: MoviesImagesRepositoryImpl): MoviesImagesRepository
}