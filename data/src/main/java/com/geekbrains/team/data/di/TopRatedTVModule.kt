package com.geekbrains.team.data.di

import com.geekbrains.team.data.tv.topRatedTV.repository.TopRatedTVRepositoryImpl
import com.geekbrains.team.domain.tv.topRatedTV.repository.TopRatedTVRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class TopRatedTVModule {
    @Binds
    @Singleton
    abstract fun provideRepositoryTopRatedTV(repository: TopRatedTVRepositoryImpl): TopRatedTVRepository
}