package com.geekbrains.team.domain.movies.topRatedMovies.repository

import com.geekbrains.team.domain.movies.topRatedMovies.model.TopRatedMovie
import io.reactivex.Single

interface TopMoviesRepository {
    fun fetch(page: Int): Single<List<TopRatedMovie>>
}