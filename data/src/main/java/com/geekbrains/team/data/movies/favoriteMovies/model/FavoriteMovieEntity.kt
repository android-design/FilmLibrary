package com.geekbrains.team.data.movies.favoriteMovies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteMovieEntity(
    @PrimaryKey
    val id: Int
)