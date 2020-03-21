package com.geekbrains.team.domain.repository

import com.geekbrains.team.domain.model.Movie
import io.reactivex.Single

interface MoviesRepository {
    fun getMovieById(id: Int, language: String): Single<Movie>
    fun searchMovies(keyWord: String, language: String): Single<List<Movie>>
}