package com.geekbrains.team.data.movies.movieCreadits.service

import com.geekbrains.team.data.movies.movieCreadits.service.model.MovieCreditsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieCreditsApi {
    @GET("3/movie/{movie_id}/credits?")
    fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<MovieCreditsResponse>
}