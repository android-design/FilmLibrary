package com.geekbrains.team.data.di

import com.geekbrains.team.data.actors.details.repository.ActorDetailsRepositoryImpl
import com.geekbrains.team.domain.actors.details.repository.ActorDetailsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ActorDetailsModule {
    @Binds
    @Singleton
    abstract fun provideActorMovieCreditsRepository(repository: ActorDetailsRepositoryImpl): ActorDetailsRepository
}