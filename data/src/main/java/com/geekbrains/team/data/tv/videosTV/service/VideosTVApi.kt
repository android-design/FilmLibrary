package com.geekbrains.team.data.tv.videosTV.service

import com.geekbrains.team.data.common.videos.model.ResponseVideos
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideosTVApi {
    @GET("/3/tv/{id}/videos?")
    fun getTVVideos(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Single<ResponseVideos>
}