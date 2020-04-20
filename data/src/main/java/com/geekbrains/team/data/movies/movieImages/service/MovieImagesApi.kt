package com.geekbrains.team.data.movies.movieImages.service

import com.geekbrains.team.data.movies.movieDetails.service.model.MovieDetailsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieImagesApi {
    @GET("/3/movie/{movie_id}/images?")
    fun getMovieImages(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Path("id") id: Int
    ): Single<MovieDetailsResponse>
}