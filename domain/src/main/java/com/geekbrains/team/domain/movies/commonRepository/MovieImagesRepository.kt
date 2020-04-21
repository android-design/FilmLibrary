package com.geekbrains.team.domain.movies.commonRepository

import io.reactivex.Single

interface MovieImagesRepository {
    fun fetch(id: Int): Single<List<String>>
}