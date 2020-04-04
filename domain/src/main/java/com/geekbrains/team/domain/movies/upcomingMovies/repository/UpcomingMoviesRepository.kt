package com.geekbrains.team.domain.movies.upcomingMovies.repository

import com.geekbrains.team.domain.movies.model.Movie
import io.reactivex.Single

interface UpcomingMoviesRepository {
    fun fetch(page: Int): Single<List<Movie>>
}