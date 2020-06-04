package com.geekbrains.team.data.tv.details.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.tv.details.service.DetailsTVApi
import com.geekbrains.team.data.tv.details.service.model.toTVShow
import com.geekbrains.team.domain.tv.details.rerpository.DetailsTVRepository
import com.geekbrains.team.domain.tv.model.TVShow
import io.reactivex.Single
import javax.inject.Inject

class DetailsTVRepositoryImpl @Inject constructor(
    private val apiDetails: DetailsTVApi
):
    DetailsTVRepository {

    override fun fetch(id: Int): Single<TVShow> {
        return apiDetails.getDetailsTV(id = id, apiKey = BuildConfig.API_KEY, language = Const.LANGUAGE)
            .map { it.toTVShow() }
    }

}