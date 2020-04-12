package com.geekbrains.team.domain.search.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.base.model.Genre
import com.geekbrains.team.domain.base.model.MovieAndTVShow
import com.geekbrains.team.domain.movies.commonRepository.MoviesGenresRepository
import com.geekbrains.team.domain.movies.commonRepository.TVGenresRepository
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.search.repository.SearchMoviesRepository
import com.geekbrains.team.domain.search.repository.SearchTVShowRepository
import com.geekbrains.team.domain.tv.model.TVShow
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.lang.IllegalArgumentException
import javax.inject.Inject

class GetSearchedResult @Inject constructor(
    private val repositoryMovies: SearchMoviesRepository,
    private val repositoryTVShow: SearchTVShowRepository,
    private val repositoryMoviesGenres: MoviesGenresRepository,
    private val repositoryTVGenres: TVGenresRepository
) :
    UseCase<List<MovieAndTVShow>, GetSearchedResult.Params> {
    override fun execute(params: Params): Single<List<MovieAndTVShow>> {
        when {
            params.isNeedSearchMovies && params.isNeedSearchTVShows -> {
                return Single.zip(
                    repositoryMovies.fetch(
                        query = params.query,
                        releaseYear = params.releaseYear,
                        page = params.page
                    ),
                    repositoryTVShow.fetch(query = params.query, page = params.page),
                    BiFunction { t1, t2 -> listOf(t1, t2).flatten() }
                )
            }
            params.isNeedSearchMovies -> {
                return Single.zip(
                    repositoryMoviesGenres.fetch(),
                    repositoryMovies.fetch(
                        query = params.query,
                        releaseYear = params.releaseYear,
                        page = params.page
                    ), BiFunction { moviesGenres, movies ->
                        concatMoviesAndGenres(moviesGenres, movies)
                    }
                )
            }
            params.isNeedSearchTVShows -> {
                return Single.zip(repositoryTVGenres.fetch(),
                    repositoryTVShow.fetch(query = params.query, page = params.page),
                    BiFunction { tvGenres, tv -> concatTVShowsAndGenres(tvGenres, tv) })
            }
            else -> throw IllegalArgumentException()
        }
    }

    private fun concatMoviesAndGenres(
        moviesGenres: List<Genre>,
        movies: List<Movie>
    ): List<MovieAndTVShow> =
        movies.apply {
            val genresMoviesMap = moviesGenres.map { it.id to it.name }.toMap()

            map { movie ->
                movie.genreIds?.map {
                    val result = genresMoviesMap[it]
                    result?.let { movie.genres.add(result) }
                }
            }
        }

    private fun concatTVShowsAndGenres(
        tvGenres: List<Genre>,
        tv: List<TVShow>
    ): List<MovieAndTVShow> =
        tv.apply {
            val genresTVMap = tvGenres.map { it.id to it.name }.toMap()

            map { tvShow ->
                tvShow.genreIds?.map {
                    val result = genresTVMap[it]
                    result?.let { tvShow.genres.add(result) }
                }
            }
        }

    data class Params(
        val query: String,
        val releaseYear: Int?,
        val page: Int,
        val isNeedSearchMovies: Boolean,
        val isNeedSearchTVShows: Boolean
    )
}