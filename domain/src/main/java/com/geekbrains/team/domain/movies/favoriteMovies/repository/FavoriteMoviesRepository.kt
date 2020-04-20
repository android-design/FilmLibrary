package com.geekbrains.team.domain.movies.favoriteMovies.repository

import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteMoviesRepository {
    fun getFavoriteMoviesIds(): Single<List<Int>>
    fun addFavoriteMovieId(id: Int): Completable
    fun deleteMovieFromFavorite(id: Int): Completable
}