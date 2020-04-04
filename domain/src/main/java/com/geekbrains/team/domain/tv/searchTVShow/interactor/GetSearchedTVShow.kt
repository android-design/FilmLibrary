package com.geekbrains.team.domain.tv.searchTVShow.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.searchTVShow.repository.SearchTVShowRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSearchedTVShow @Inject constructor(private val repository: SearchTVShowRepository) :
    UseCase<List<TVShow>, GetSearchedTVShow.Params> {
    override fun execute(params: Params): Single<List<TVShow>> {
        return repository.fetch(
            query = params.query,
            page = params.params
        )
    }

    data class Params(val query: String, val params: Int)
}