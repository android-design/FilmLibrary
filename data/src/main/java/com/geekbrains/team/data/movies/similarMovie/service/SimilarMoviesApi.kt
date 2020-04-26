package com.geekbrains.team.data.movies.similarMovie.service

import com.geekbrains.team.data.movies.similarMovie.service.model.SimilarMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimilarMoviesApi {
    @GET("/3/movie/{movie_id}/similar?")
    fun getSimilarMovies(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<SimilarMoviesResponse>
}