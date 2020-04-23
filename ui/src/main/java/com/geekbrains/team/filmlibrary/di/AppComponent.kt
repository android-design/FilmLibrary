package com.geekbrains.team.filmlibrary.di

import android.content.Context
import com.geekbrains.team.data.di.*
import com.geekbrains.team.filmlibrary.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, DatabaseModule::class, RemoteModule::class,
        MainScreenFragmentModule::class, SearchFragmentModule::class, FavoriteFragmentModule::class,
        TopFragmentsModule::class, UpcomingMoviesModule::class, SearchMoviesModule::class,
        SearchTVModule::class, GenresModule::class, FavoriteMoviesModule::class, NowPlayingModule::class,
        TopRatedMoviesModule::class, MovieDetailsModule::class, TopRatedTVModule::class, ImagesModule::class,
        VideosModule::class, FullFilmInfoFragmentModule::class, MovieCreditsModule::class, MovieImagesModule::class]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}