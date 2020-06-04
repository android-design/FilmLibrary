package com.geekbrains.team.domain.tv.similarTvShows.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.similarTvShows.repository.SimilarTVShowsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSimilarTVShowsUseCase @Inject constructor(
    private val repository: SimilarTVShowsRepository
): UseCase<List<TVShow>, GetSimilarTVShowsUseCase.Params> {
    data class Params(
        val id: Int,
        val page: Int
    )

    override fun execute(params: Params): Single<out List<TVShow>> {
        return repository.fetch(id = params.id, page = params.page)
    }
}