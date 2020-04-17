package com.geekbrains.team.data.movies.favoriteMovie.repository

import com.geekbrains.team.data.movies.favoriteMovie.dao.FavoriteMoviesDao
import com.geekbrains.team.data.movies.favoriteMovie.model.FavoriteMovieEntity
import com.geekbrains.team.domain.movies.favoriteMovie.repository.FavoriteMoviesRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteMoviesRepositoryImpl @Inject constructor(private val dao: FavoriteMoviesDao) :
    FavoriteMoviesRepository {

    override fun getFavoriteMoviesIds(): Single<List<Int>> = dao.getAllMoviesIds()

    override fun addFavoriteMovieId(id: Int): Completable = dao.insert(FavoriteMovieEntity(id = id))

    override fun deleteMovieFromFavorite(id: Int): Completable = dao.delete(id)

}