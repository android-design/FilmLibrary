package com.geekbrains.team.domain.movies.movieDetails.repository

import com.geekbrains.team.domain.movies.model.Movie
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieDetailsRepository {
    fun getMovie(id: Int): Single<Movie>
    fun getMovieList(ids: List<Int>): Flowable<List<Movie>>
}