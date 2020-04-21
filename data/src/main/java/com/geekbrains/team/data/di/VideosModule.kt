package com.geekbrains.team.data.di

import com.geekbrains.team.data.movies.videosMovies.repository.MoviesVideosRepositoryImpl
import com.geekbrains.team.data.tv.videosTV.repository.TVVideosRepositoryImpl
import com.geekbrains.team.domain.movies.commonRepository.VideosRepository
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class VideosModule {
    @Binds
    @Singleton
    @Named("MovieVideos")
    abstract fun provideRepositoryMoviesVideos(repository: MoviesVideosRepositoryImpl): VideosRepository

    @Binds
    @Singleton
    @Named("TVVideos")
    abstract fun provideRepositoryTVVideos(repository: TVVideosRepositoryImpl): VideosRepository
}