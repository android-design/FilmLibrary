package com.geekbrains.team.domain.tv.nowPlayingTV.repository

import com.geekbrains.team.domain.tv.model.TVShow
import io.reactivex.Single

interface NowPlayingTVRepository {
    fun fetch(page: Int? = null): Single<List<TVShow>>
}