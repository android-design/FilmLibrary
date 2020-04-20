package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.nowPlayingMovies.repository.NowPlayingMoviesRepositoryImpl
import com.geekbrains.team.data.tv.nowPlayingTV.repository.NowPlayingTVRepositoryImpl
import com.geekbrains.team.domain.movies.nowPlayingMovies.repository.NowPlayingMoviesRepository
import com.geekbrains.team.domain.tv.nowPlayingTV.repository.NowPlayingTVRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NowPlayingModule {
    @Binds
    @Singleton
    abstract fun provideRepositoryNowPlayingMovies(repository: NowPlayingMoviesRepositoryImpl): NowPlayingMoviesRepository

    @Binds
    @Singleton
    abstract fun provideRepositoryNowPlayingTV(repository: NowPlayingTVRepositoryImpl): NowPlayingTVRepository
}