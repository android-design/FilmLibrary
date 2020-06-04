package com.geekbrains.team.data.tv.similar.service

import com.geekbrains.team.data.tv.similar.service.model.SimilarTVShowsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimilarTVShowsApi {
    @GET("/3/tv/{tv_id}/similar?")
    fun getSimilarTVShows(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<SimilarTVShowsResponse>
}