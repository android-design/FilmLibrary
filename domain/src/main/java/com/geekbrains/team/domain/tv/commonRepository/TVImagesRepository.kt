package com.geekbrains.team.domain.tv.commonRepository

import com.geekbrains.team.domain.base.model.Images
import io.reactivex.Single

interface TVImagesRepository {
    fun fetch(id: Int): Single<Images>
}