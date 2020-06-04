package com.geekbrains.team.data.di

import com.geekbrains.team.data.tv.details.repository.DetailsTVRepositoryImpl
import com.geekbrains.team.domain.tv.details.rerpository.DetailsTVRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DetailsTVModule {
    @Binds
    @Singleton
    abstract fun provideDetailsTVRepository(repository: DetailsTVRepositoryImpl): DetailsTVRepository
}