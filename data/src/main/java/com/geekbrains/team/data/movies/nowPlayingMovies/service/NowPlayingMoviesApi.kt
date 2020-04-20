package com.geekbrains.team.data.movies.nowPlayingMovies.service

import com.geekbrains.team.data.movies.nowPlayingMovies.service.model.ResponseNowPlayingMovies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingMoviesApi {
    @GET("/3/movie/now_playing?")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int?
    ): Single<ResponseNowPlayingMovies>
}