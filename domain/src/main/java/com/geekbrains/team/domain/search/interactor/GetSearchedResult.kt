package com.geekbrains.team.domain.search.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.base.model.MovieAndTVShow
import com.geekbrains.team.domain.search.repository.SearchMoviesRepository
import com.geekbrains.team.domain.tv.searchTVShow.repository.SearchTVShowRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GetSearchedResult @Inject constructor(
    private val repositoryMovies: SearchMoviesRepository,
    private val repositoryTVShow: SearchTVShowRepository
) :
    UseCase<List<MovieAndTVShow>, GetSearchedResult.Params> {
    override fun execute(params: Params): Single<List<MovieAndTVShow>> {
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

    data class Params(val query: String, val releaseYear: Int?, val page: Int)
}