package com.geekbrains.team.domain.movies.topRatedMovies.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.topRatedMovies.repository.TopRatedMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTopRatedMovies @Inject constructor(private val repository: TopRatedMoviesRepository) :
    UseCase<MutableList<Movie>, GetTopRatedMovies.Params> {
    override fun execute(params: Params): Single<MutableList<Movie>> =
        repository.fetch(params.page)

    data class Params(val page: Int)
}