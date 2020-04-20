package com.geekbrains.team.domain.movies.commonRepository

import com.geekbrains.team.domain.base.model.Images
import io.reactivex.Single

interface MoviesImagesRepository {
    fun fetch(id: Int): Single<Images>
}