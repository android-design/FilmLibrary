package com.geekbrains.team.domain.movies.upcomingMovies.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.upcomingMovies.repository.UpcomingMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUpcomingMovies @Inject constructor(private val repository: UpcomingMoviesRepository) :
    UseCase<List<Movie>, GetUpcomingMovies.Params> {
    override fun execute(params: Params): Single<List<Movie>> = repository.fetch(page = params.page)

    data class Params(val page: Int)
}