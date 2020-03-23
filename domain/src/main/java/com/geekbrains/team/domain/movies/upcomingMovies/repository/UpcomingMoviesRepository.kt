package com.geekbrains.team.domain.movies.upcomingMovies.repository

import com.geekbrains.team.domain.movies.upcomingMovies.model.UpcomingMovie
import io.reactivex.Single

interface UpcomingMoviesRepository {
    fun fetch(page: Int): Single<List<UpcomingMovie>>
}