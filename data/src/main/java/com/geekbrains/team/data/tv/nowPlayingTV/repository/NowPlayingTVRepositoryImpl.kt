package com.geekbrains.team.data.tv.nowPlayingTV.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const.LANGUAGE
import com.geekbrains.team.data.tv.nowPlayingTV.service.NowPlayingTVApi
import com.geekbrains.team.data.tv.nowPlayingTV.service.model.toTVShow
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.nowPlayingTV.repository.NowPlayingTVRepository
import io.reactivex.Single
import javax.inject.Inject

class NowPlayingTVRepositoryImpl @Inject constructor(private val api: NowPlayingTVApi) :
    NowPlayingTVRepository {

    override fun fetch(page: Int?): Single<List<TVShow>> =
        api.getNowPlayingTV(apiKey = BuildConfig.API_KEY, language = LANGUAGE, page = page)
            .map { response -> response.toTVShow() }
}