package com.geekbrains.team.data.di

import com.geekbrains.team.data.tv.similar.repository.SimilarTVShowsRepositoryImpl
import com.geekbrains.team.domain.tv.similarTvShows.repository.SimilarTVShowsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class SimilarTVShowsModule {
    @Binds
    @Singleton
    abstract fun provideRepositorySimilarTVShows(repository: SimilarTVShowsRepositoryImpl): SimilarTVShowsRepository
}