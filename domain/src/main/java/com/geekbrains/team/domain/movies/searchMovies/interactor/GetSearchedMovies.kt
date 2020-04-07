package com.geekbrains.team.domain.movies.searchMovies.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.searchMovies.repository.SearchMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSearchedMovies @Inject constructor(private val repository: SearchMoviesRepository) :
    UseCase<List<Movie>, GetSearchedMovies.Params> {
    override fun execute(params: Params): Single<List<Movie>> {
        return repository.fetch(
            query = params.query,
            releaseYear = params.releaseYear,
            page = params.page
        )
    }

    data class Params(val query: String, val releaseYear: Int, val page: Int)
}