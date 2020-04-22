package com.geekbrains.team.data.tv.genresTV.service

import com.geekbrains.team.data.common.genre.model.ResponseGenres
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresTVApi {
    @GET("/3/genre/tv/list?")
    fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Single<ResponseGenres>
}