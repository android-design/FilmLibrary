package com.geekbrains.team.data.tv.topRatedTV.service

import com.geekbrains.team.data.tv.topRatedTV.service.model.ResponseTopRatedTV
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedTVApi {
    @GET("/3/tv/top_rated?")
    fun getTopTV(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<ResponseTopRatedTV>
}