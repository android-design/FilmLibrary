package com.geekbrains.team.domain.tv.topRatedTV.repository

import com.geekbrains.team.domain.tv.model.TVShow
import io.reactivex.Single

interface TopRatedTVRepository {
    fun fetch(page: Int): Single<MutableList<TVShow>>
}