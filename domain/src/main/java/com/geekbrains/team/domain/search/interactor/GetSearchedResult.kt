package com.geekbrains.team.domain.search.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.base.model.MovieAndTVShow
import com.geekbrains.team.domain.movies.commonRepository.MoviesGenresRepository
import com.geekbrains.team.domain.movies.commonRepository.TVGenresRepository
import com.geekbrains.team.domain.movies.model.fillMoviesGenres
import com.geekbrains.team.domain.search.repository.SearchMoviesRepository
import com.geekbrains.team.domain.search.repository.SearchTVRepository
import com.geekbrains.team.domain.tv.model.fillTVGenres
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function4
import javax.inject.Inject

class GetSearchedResult @Inject constructor(
    private val repositoryMovies: SearchMoviesRepository,
    private val repositoryTV: SearchTVRepository,
    private val repositoryMoviesGenres: MoviesGenresRepository,
    private val repositoryTVGenres: TVGenresRepository
) :
    UseCase<List<MovieAndTVShow>, GetSearchedResult.Params> {
    override fun execute(params: Params): Single<List<MovieAndTVShow>> {
        when {
            params.isNeedSearchMovies && params.isNeedSearchTVShows -> {
                return Single.zip(
                    repositoryMoviesGenres.fetch(),
                    repositoryTVGenres.fetch(),
                    repositoryMovies.fetch(
                        query = params.query,
                        releaseYear = params.releaseYear,
                        page = params.page
                    ),
                    repositoryTV.fetch(query = params.query, page = params.page),
                    Function4 { moviesGenres, tvGenres, movies, tv ->
                        listOf(
                            fillMoviesGenres(moviesGenres, movies),
                            fillTVGenres(tvGenres, tv)
                        ).flatten()
                    }
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
                        fillMoviesGenres(moviesGenres, movies)
                    }
                )
            }
            params.isNeedSearchTVShows -> {
                return Single.zip(repositoryTVGenres.fetch(),
                    repositoryTV.fetch(query = params.query, page = params.page),
                    BiFunction { tvGenres, tv -> fillTVGenres(tvGenres, tv) })
            }
            else -> throw IllegalArgumentException()
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