package com.geekbrains.team.domain.movies.topRatedMovies.repository

import com.geekbrains.team.domain.movies.model.Movie
import io.reactivex.Single

interface TopMoviesRepository {
    fun fetch(page: Int): Single<List<Movie>>
}