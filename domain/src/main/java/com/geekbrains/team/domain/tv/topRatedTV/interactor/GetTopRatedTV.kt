package com.geekbrains.team.domain.tv.topRatedTV.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.topRatedTV.repository.TopRatedTVRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTopRatedTV @Inject constructor(private val repository: TopRatedTVRepository) :
    UseCase<List<TVShow>, GetTopRatedTV.Params> {
    override fun execute(params: Params): Single<MutableList<TVShow>> =
        repository.fetch(params.page)

    data class Params(val page: Int)
}