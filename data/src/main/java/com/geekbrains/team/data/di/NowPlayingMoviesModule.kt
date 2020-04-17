package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.nowPlayingMovies.repository.NowPlayingMoviesRepositoryImpl
import com.geekbrains.team.domain.movies.nowPlayingMovies.repository.NowPlayingMoviesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NowPlayingMoviesModule {
    @Binds
    @Singleton
    abstract fun provideRepositoryNowPlayingMovies(repository: NowPlayingMoviesRepositoryImpl): NowPlayingMoviesRepository
}