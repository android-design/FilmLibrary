package com.geekbrains.team.domain.repository

import com.geekbrains.team.domain.model.Movie
import io.reactivex.Single

interface TopMoviesRepository {
    fun getTopMovies(language: String): Single<List<Movie>>
}