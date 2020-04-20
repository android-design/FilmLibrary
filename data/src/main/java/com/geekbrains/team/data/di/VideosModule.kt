package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.videosMovies.repository.MoviesVideosRepositoryImpl
import com.geekbrains.team.domain.movies.commonRepository.VideosRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class VideosModule {
    @Binds
    @Singleton
    abstract fun provideRepositoryMoviesVideos(repository: MoviesVideosRepositoryImpl): VideosRepository
}