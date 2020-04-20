package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.favoriteMovies.repository.FavoriteMoviesRepositoryImpl
import com.geekbrains.team.domain.movies.favoriteMovies.repository.FavoriteMoviesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class FavoriteMoviesModule {

    @Binds
    @Singleton
    abstract fun provideRepositoryFavoriteMovies(repository: FavoriteMoviesRepositoryImpl): FavoriteMoviesRepository
}