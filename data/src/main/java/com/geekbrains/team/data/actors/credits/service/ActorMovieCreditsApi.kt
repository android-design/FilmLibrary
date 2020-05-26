package com.geekbrains.team.data.actors.credits.service

import com.geekbrains.team.data.actors.credits.service.model.ActorCreditsResponse
import com.geekbrains.team.data.movies.detailsMovies.service.model.MovieDetailsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ActorMovieCreditsApi {
    @GET("/3/person/{person_id}/movie_credits?")
    fun getActorMovieCredits(
        @Path("person_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<ActorCreditsResponse>
}