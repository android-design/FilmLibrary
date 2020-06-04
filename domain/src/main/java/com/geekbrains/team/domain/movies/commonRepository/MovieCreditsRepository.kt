package com.geekbrains.team.domain.movies.commonRepository

import com.geekbrains.team.domain.base.model.Credits
import io.reactivex.Single

interface MovieCreditsRepository {
    fun fetch(id: Int): Single<Credits>
}