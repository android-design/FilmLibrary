package com.geekbrains.team.data.movies.favoriteMovie.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geekbrains.team.data.movies.favoriteMovie.model.FavoriteMovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: FavoriteMovieEntity): Completable

    @Query("DELETE FROM FavoriteMovieEntity WHERE id = :movieId")
    fun delete(movieId: Int): Completable

    @Query("SELECT id FROM FavoriteMovieEntity")
    fun getAllMoviesIds(): Single<List<Int>>
}