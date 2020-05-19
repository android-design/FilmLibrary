package com.geekbrains.team.data.di

import com.geekbrains.team.data.actors.credits.repository.ActorMovieCreditsRepositoryImpl
import com.geekbrains.team.data.actors.credits.repository.ActorTVCreditsRepositoryImpl
import com.geekbrains.team.domain.actors.details.repository.ActorMovieCreditsRepository
import com.geekbrains.team.domain.actors.details.repository.ActorTVCreditsRepository
import dagger.Binds
import javax.inject.Singleton

abstract class ActorCreditsModule {
    @Binds
    @Singleton
    abstract fun provideActorMovieCreditsRepository(repository: ActorMovieCreditsRepositoryImpl): ActorMovieCreditsRepository

    @Binds
    @Singleton
    abstract fun provideActorTVCreditsRepository(repository: ActorTVCreditsRepositoryImpl): ActorTVCreditsRepository
}