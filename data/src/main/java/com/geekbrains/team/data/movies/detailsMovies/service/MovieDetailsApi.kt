package com.geekbrains.team.data.movies.detailsMovies.service

import com.geekbrains.team.data.movies.detailsMovies.service.model.MovieDetailsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieDetailsApi {
    @GET("/3/movie/{id}?")
    fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<MovieDetailsResponse>
}