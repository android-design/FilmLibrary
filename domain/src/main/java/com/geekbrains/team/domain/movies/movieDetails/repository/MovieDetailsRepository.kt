package com.geekbrains.team.domain.movies.movieDetails.repository

import com.geekbrains.team.domain.movies.model.Movie
import io.reactivex.Single

interface MovieDetailsRepository {
    fun fetch(id: Int): Single<Movie>
}