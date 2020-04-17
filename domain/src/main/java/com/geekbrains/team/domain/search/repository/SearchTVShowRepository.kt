package com.geekbrains.team.domain.search.repository

import com.geekbrains.team.domain.tv.model.TVShow
import io.reactivex.Single

interface SearchTVShowRepository {
    fun fetch(query: String, page: Int): Single<List<TVShow>>
}