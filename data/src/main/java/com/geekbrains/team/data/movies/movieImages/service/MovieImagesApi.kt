package com.geekbrains.team.data.movies.movieImages.service

import com.geekbrains.team.data.movies.movieImages.service.model.MovieImagesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieImagesApi {
    @GET("/3/movie/{movie_id}/images?")
    fun getMovieImages(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<MovieImagesResponse>
}