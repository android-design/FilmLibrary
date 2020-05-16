package com.geekbrains.team.domain.movies.searchMovies.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.movies.commonRepository.MoviesGenresRepository
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.model.fillMovieGenres
import com.geekbrains.team.domain.movies.searchMovies.repository.SearchMoviesRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GetSearchedMovies @Inject constructor(
    private val repositoryMovies: SearchMoviesRepository,
    private val repositoryMoviesGenres: MoviesGenresRepository
) :
    UseCase<List<Movie>, GetSearchedMovies.Params> {
    override fun execute(params: Params): Single<List<Movie>> =
        Single.zip(
            repositoryMoviesGenres.fetch(),
            repositoryMovies.fetch(
                query = params.query,
                page = params.page
            ),
            BiFunction { moviesGenres, movies ->
                movies.map { it.apply { fillMovieGenres(moviesGenres, it) } }
            }
        )

    data class Params(
        val query: String,
        val page: Int? = null
    )
}