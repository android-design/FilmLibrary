package com.geekbrains.team.data.movies.upcomingMovies.service

import com.geekbrains.team.data.movies.upcomingMovies.service.model.ResponseUpcomingMovies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingMoviesApi {
    @GET("/3/movie/upcoming?api_key=4ceb7344638e5de54a00b9693a736705&language=en-US&page=1")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<ResponseUpcomingMovies>
}