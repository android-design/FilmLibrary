package com.geekbrains.team.data.movies.upcomingMovies.service

import com.geekbrains.team.data.movies.upcomingMovies.service.model.ResponseTopRatedMovies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TopMoviesApi {
    @GET("/3/movie/top_rated?api_key=<<api_key>>&language=en-US&page=1")
    fun getTopMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<ResponseTopRatedMovies>
}