package com.geekbrains.team.data.movies.movieDetails.service

import com.geekbrains.team.data.movies.movieDetails.service.model.MovieDetailsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieDetailsApi {
    @GET("/3/movie/{id}?")
    fun getMovieDetails(
        @Query("api_key") apiKey: String,
        @Query("language")language: String,
        @Path("id") id: Int): Single<MovieDetailsResponse>
}