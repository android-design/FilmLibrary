package com.geekbrains.team.domain.tv.nowPlayingTV.interactor

import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.nowPlayingTV.repository.NowPlayingTVRepository
import io.reactivex.Single
import javax.inject.Inject

class GetFirstNowPlayingTV @Inject constructor(private val repository: NowPlayingTVRepository) :
    UseCase<TVShow, None> {
    override fun execute(params: None): Single<TVShow> =
        repository.fetch()
            .flatMap { tv -> Single.just(tv.first()) }
}