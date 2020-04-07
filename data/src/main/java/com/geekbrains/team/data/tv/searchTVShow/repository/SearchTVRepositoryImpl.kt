package com.geekbrains.team.data.movies.searchMovies.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.movies.searchMovies.service.SearchTVShowApi
import com.geekbrains.team.data.tv.searchTVShow.service.model.toTVShow
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.searchTVShow.repository.SearchTVShowRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchTVRepositoryImpl @Inject constructor(private val api: SearchTVShowApi) :
    SearchTVShowRepository {
    override fun fetch(query: String, page: Int)
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