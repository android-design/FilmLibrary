package com.geekbrains.team.data.movies.upcomingMovies.service

import com.geekbrains.team.data.movies.upcomingMovies.service.model.ResponseUpcomingMovies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingMoviesApi {
    @GET("/3/movie/upcoming?")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<ResponseUpcomingMovies>
}