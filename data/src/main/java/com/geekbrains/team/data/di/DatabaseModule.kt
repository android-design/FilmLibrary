package com.geekbrains.team.data.di

import android.content.Context
import androidx.room.Room
import com.geekbrains.team.data.movies.favoriteMovie.database.AppDatabase
import com.geekbrains.team.data.movies.favoriteMovie.database.FavoriteMoviesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(context,
            AppDatabase::class.java,
            "FavoriteMovies.db").build()
    }

    @Singleton
    @Provides
    fun provideFavoriteMoviesDao(database: AppDatabase): FavoriteMoviesDao {
        return database.favoriteMoviesDao()
    }
}