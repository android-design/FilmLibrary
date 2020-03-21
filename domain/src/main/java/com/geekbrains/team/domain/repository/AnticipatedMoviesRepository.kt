package com.geekbrains.team.domain.repository

import com.geekbrains.team.domain.model.Movie
import io.reactivex.Single

interface AnticipatedMoviesRepository {
    fun getMovies(page: Int, language: String): Single<List<Movie>>
}