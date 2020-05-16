package com.geekbrains.team.data.tv.searchTV.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.tv.searchTV.service.SearchTVApi
import com.geekbrains.team.data.tv.searchTV.service.model.toTVShow
import com.geekbrains.team.domain.tv.searchTV.repository.SearchTVRepository
import com.geekbrains.team.domain.tv.model.TVShow
import io.reactivex.Single
import javax.inject.Inject

class SearchTVRepositoryImpl @Inject constructor(private val api: SearchTVApi) :
    SearchTVRepository {
    override fun fetch(query: String, page: Int?)
            : Single<List<TVShow>> {
        return api.getSearchTVShow(
            apiKey = BuildConfig.API_KEY,
            language = Const.LANGUAGE,
            query = query,
            page = page
        )
            .map { response ->
                response.toTVShow()
            }
    }
}