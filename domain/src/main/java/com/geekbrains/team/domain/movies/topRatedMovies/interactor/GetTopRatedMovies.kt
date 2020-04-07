package com.geekbrains.team.domain.movies.topRatedMovies.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.topRatedMovies.repository.TopMoviesRepository
import io.reactivex.Single

class GetTopRatedMovies(private val repository: TopMoviesRepository) :
    UseCase<List<Movie>, GetTopRatedMovies.Params> {
    override fun execute(params: Params): Single<List<Movie>> {
        return repository.fetch(page = params.page)
    }

    data class Params(val page: Int)
}