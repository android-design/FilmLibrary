package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.genresMovies.repository.MoviesGenresRepositoryImpl
import com.geekbrains.team.data.tv.genresTV.repository.TVGenresRepositoryImpl
import com.geekbrains.team.domain.movies.commonRepository.MoviesGenresRepository
import com.geekbrains.team.domain.movies.commonRepository.TVGenresRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class GenresModule {
    @Binds
    @Singleton
    abstract fun provideRepositoryMoviesGenres(repository: MoviesGenresRepositoryImpl): MoviesGenresRepository

    @Binds
    @Singleton
    abstract fun provideRepositoryTVGenres(repository: TVGenresRepositoryImpl): TVGenresRepository
}