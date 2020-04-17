package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.favoriteMovie.repository.FavoriteMoviesRepositoryImpl
import com.geekbrains.team.domain.movies.favoriteMovie.repository.FavoriteMoviesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class FavoriteMoviesModule {

    @Binds
    @Singleton
    abstract fun provideRepositoryFavoriteMovies(repository: FavoriteMoviesRepositoryImpl): FavoriteMoviesRepository
}