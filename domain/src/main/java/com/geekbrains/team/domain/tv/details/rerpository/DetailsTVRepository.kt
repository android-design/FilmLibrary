package com.geekbrains.team.domain.tv.details.rerpository

import com.geekbrains.team.domain.tv.model.TVShow
import io.reactivex.Single

interface DetailsTVRepository {
    fun fetch(id: Int): Single<TVShow>
}