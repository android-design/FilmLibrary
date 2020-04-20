package com.geekbrains.team.data.tv.nowPlayingTV.service

import com.geekbrains.team.data.tv.nowPlayingTV.service.model.ResponseNowPlayingTV
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingTVApi {
    @GET("/3/tv/on_the_air?")
    fun getNowPlayingTV(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int?
    ): Single<ResponseNowPlayingTV>
}