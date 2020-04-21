package com.geekbrains.team.domain.tv.commonRepository

import com.geekbrains.team.domain.base.model.Genre
import io.reactivex.Single

interface TVGenresRepository {
    fun fetch(): Single<List<Genre>>
}