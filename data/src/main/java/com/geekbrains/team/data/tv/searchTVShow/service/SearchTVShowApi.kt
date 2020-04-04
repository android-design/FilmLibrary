package com.geekbrains.team.data.movies.searchMovies.service

import com.geekbrains.team.data.tv.searchTVShow.service.model.ResponseSearchTVShow
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchTVShowApi {
    @GET("/search/tv?")
    fun getSearchTVShow(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int

    ): Single<ResponseSearchTVShow>
}