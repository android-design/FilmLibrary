package com.geekbrains.team.domain.tv.searchTV.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.tv.searchTV.repository.SearchTVRepository
import com.geekbrains.team.domain.tv.commonRepository.TVGenresRepository
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.model.fillTVGenres
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GetSearchedTV @Inject constructor(
    private val repositoryTV: SearchTVRepository,
    private val repositoryTVGenres: TVGenresRepository
) :
    UseCase<List<TVShow>, GetSearchedTV.Params> {
    override fun execute(params: Params): Single<List<TVShow>> =
        Single.zip(
            repositoryTVGenres.fetch(),
            repositoryTV.fetch(
                query = params.query,
                page = params.page
            ),
            BiFunction { tvGenres, tv ->
                fillTVGenres(tvGenres, tv)
            }
        )

    data class Params(
        val query: String,
        val page: Int? = null
    )
}