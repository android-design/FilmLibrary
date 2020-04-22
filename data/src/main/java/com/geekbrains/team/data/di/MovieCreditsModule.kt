package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.movieCreadits.repository.MovieCreditsRepositoryImpl
import com.geekbrains.team.domain.movies.commonRepository.MovieCreditsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module

abstract class MovieCreditsModule {

    @Binds

    @Singleton

    abstract fun provideMovieCreditsRepository(repository: MovieCreditsRepositoryImpl): MovieCreditsRepository

}