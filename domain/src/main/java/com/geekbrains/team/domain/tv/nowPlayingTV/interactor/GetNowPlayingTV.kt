package com.geekbrains.team.domain.tv.nowPlayingTV.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.nowPlayingTV.repository.NowPlayingTVRepository
import io.reactivex.Single
import javax.inject.Inject

class GetNowPlayingTV @Inject constructor(private val repository: NowPlayingTVRepository) :
    UseCase<List<TVShow>, GetNowPlayingTV.Params> {
    override fun execute(params: Params): Single<List<TVShow>> =
        repository.fetch(page = params.page)

    data class Params(val page: Int)
}