package com.geekbrains.team.domain.movies.nowPlayingMovies.repository

import com.geekbrains.team.domain.movies.model.Movie
import io.reactivex.Single

interface NowPlayingMoviesRepository {
    fun fetch(page: Int? = null): Single<List<Movie>>
}