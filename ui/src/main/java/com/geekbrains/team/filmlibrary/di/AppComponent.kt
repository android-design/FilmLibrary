package com.geekbrains.team.filmlibrary.di

import android.content.Context
import com.geekbrains.team.data.di.RemoteModule
import com.geekbrains.team.data.di.SearchMoviesModule
import com.geekbrains.team.data.di.SearchTVShowModule
import com.geekbrains.team.data.di.UpcomingMoviesModule
import com.geekbrains.team.filmlibrary.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, RemoteModule::class, MainScreenFragmentModule::class, SearchFragmentModule::class, UpcomingMoviesModule::class, SearchMoviesModule::class, SearchTVShowModule::class])
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}