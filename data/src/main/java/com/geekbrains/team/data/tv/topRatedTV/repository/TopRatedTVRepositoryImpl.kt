package com.geekbrains.team.data.tv.topRatedTV.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const.LANGUAGE
import com.geekbrains.team.data.tv.topRatedTV.service.TopRatedTVApi
import com.geekbrains.team.data.tv.topRatedTV.service.model.toTVShow
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.topRatedTV.repository.TopRatedTVRepository
import io.reactivex.Single
import javax.inject.Inject

class TopRatedTVRepositoryImpl @Inject constructor(private val api: TopRatedTVApi) :
    TopRatedTVRepository {

    override fun fetch(page: Int): Single<MutableList<TVShow>> {
        return api.getTopTV(apiKey = BuildConfig.API_KEY, language = LANGUAGE, page = page)
            .map { response ->
                response.toTVShow()
            }
    }
}