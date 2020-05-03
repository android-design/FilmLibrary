package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.similarMovies.repository.SimilarMoviesRepositoryImpl
import com.geekbrains.team.domain.movies.similarMovie.repository.SimilarMoviesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SimilarMoviesModule {

    @Binds
    @Singleton
    abstract fun provideSimilarMoviesRepository(repository: SimilarMoviesRepositoryImpl): SimilarMoviesRepository

}