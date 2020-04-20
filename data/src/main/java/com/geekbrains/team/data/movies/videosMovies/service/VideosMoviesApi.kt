package com.geekbrains.team.data.movies.videosMovies.service

import com.geekbrains.team.data.common.videos.model.ResponseVideos
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideosMoviesApi {
    @GET("/3/movie/{id}/videos?")
    fun getMovieVideos(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Single<ResponseVideos>
}