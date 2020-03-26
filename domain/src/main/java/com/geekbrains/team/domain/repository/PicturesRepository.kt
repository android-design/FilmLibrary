package com.geekbrains.team.domain.repository

import io.reactivex.Single

interface PicturesRepository {
    fun getPictures(id: Int, language: String): Single<List<String>>
}