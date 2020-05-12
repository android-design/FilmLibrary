package com.geekbrains.team.domain.tv.credits.repository

import com.geekbrains.team.domain.base.model.Credits
import io.reactivex.Single

interface TVCreditsRepository {
    fun fetch(id: Int): Single<Credits>
}