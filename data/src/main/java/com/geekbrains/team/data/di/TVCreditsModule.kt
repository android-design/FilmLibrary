package com.geekbrains.team.data.di

import com.geekbrains.team.data.tv.credits.repository.TVCreditsRepositoryImpl
import com.geekbrains.team.domain.tv.credits.repository.TVCreditsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class TVCreditsModule {

    @Binds
    @Singleton
    abstract fun getTVCreditsRepository(repository: TVCreditsRepositoryImpl): TVCreditsRepository

}