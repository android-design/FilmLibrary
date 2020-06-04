package com.geekbrains.team.domain.tv.similarTvShows.repository

import com.geekbrains.team.domain.tv.model.TVShow
import io.reactivex.Single

interface SimilarTVShowsRepository {
    fun fetch(id: Int, page: Int): Single<List<TVShow>>
}