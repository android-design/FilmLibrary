package com.geekbrains.team.data.tv.credits.service

import com.geekbrains.team.data.tv.credits.service.model.TVCreditsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVCreditsApi {
    @GET("3/tv/{movie_id}/credits?")
    fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<TVCreditsResponse>
}