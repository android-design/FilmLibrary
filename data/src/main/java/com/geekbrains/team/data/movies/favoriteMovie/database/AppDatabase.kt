package com.geekbrains.team.data.movies.favoriteMovie.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geekbrains.team.data.movies.favoriteMovie.model.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteMoviesDao(): FavoriteMoviesDao
}