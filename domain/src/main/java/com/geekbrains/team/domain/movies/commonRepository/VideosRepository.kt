package com.geekbrains.team.domain.movies.commonRepository

import com.geekbrains.team.domain.base.model.Video
import io.reactivex.Single

interface VideosRepository {
    fun fetch(id: Int): Single<List<Video>>
}