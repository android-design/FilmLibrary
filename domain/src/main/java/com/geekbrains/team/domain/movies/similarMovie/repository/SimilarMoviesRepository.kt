package com.geekbrains.team.domain.movies.similarMovie.repository

import com.geekbrains.team.domain.movies.model.Movie
import io.reactivex.Single

interface SimilarMoviesRepository {
    fun fetch(id: Int, page: Int?): Single<List<Movie>>
}