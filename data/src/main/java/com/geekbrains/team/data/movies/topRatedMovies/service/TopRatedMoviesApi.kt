package com.geekbrains.team.data.movies.topRatedMovies.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedMoviesApi {
    @GET("/3/movie/top_rated?")
    fun getTopMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<ResponseTopRatedMovies>
}