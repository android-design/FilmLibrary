package com.geekbrains.team.domain.movies.topRatedMovies.repository

import com.geekbrains.team.domain.movies.model.Movie
import io.reactivex.Single

interface TopRatedMoviesRepository {
    fun fetch(page: Int): Single<MutableList<Movie>>
}