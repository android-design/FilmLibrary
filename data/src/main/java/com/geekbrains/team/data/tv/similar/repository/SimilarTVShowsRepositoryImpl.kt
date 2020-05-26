package com.geekbrains.team.data.tv.similar.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.tv.similar.service.SimilarTVShowsApi
import com.geekbrains.team.data.tv.similar.service.model.toTVShowList
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.similarTvShows.interactor.GetSimilarTVShowsUseCase
import com.geekbrains.team.domain.tv.similarTvShows.repository.SimilarTVShowsRepository
import io.reactivex.Single
import javax.inject.Inject

class SimilarTVShowsRepositoryImpl @Inject constructor(
    private val api: SimilarTVShowsApi
): SimilarTVShowsRepository {
    override fun fetch(id: Int, page: Int): Single<List<TVShow>> {
        return api.getSimilarTVShows(id = id, apiKey = BuildConfig.API_KEY,
                                language = Const.LANGUAGE, page = page).map { it.toTVShowList() }
    }
}