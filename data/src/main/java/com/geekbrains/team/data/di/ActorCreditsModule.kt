package com.geekbrains.team.data.di

import com.geekbrains.team.data.actors.credits.repository.ActorMovieCreditsRepositoryImpl
import com.geekbrains.team.data.actors.credits.repository.ActorTVCreditsRepositoryImpl
import com.geekbrains.team.domain.actors.credits.repository.ActorMovieCreditsRepository
import com.geekbrains.team.domain.actors.credits.repository.ActorTVCreditsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ActorCreditsModule {
    @Binds
    @Singleton
    abstract fun provideActorMovieCreditsRepository(repository: ActorMovieCreditsRepositoryImpl): ActorMovieCreditsRepository

    @Binds
    @Singleton
    abstract fun provideActorTVCreditsRepository(repository: ActorTVCreditsRepositoryImpl): ActorTVCreditsRepository
}