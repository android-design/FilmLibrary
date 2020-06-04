package com.geekbrains.team.data.tv.details.service

import com.geekbrains.team.data.tv.details.service.model.DetailsTVResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsTVApi {
    @GET("/3/tv/{tv_id}?")
    fun getDetailsTV(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<DetailsTVResponse>
}