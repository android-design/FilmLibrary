package com.geekbrains.team.data.movies.searchMovies.service

import com.geekbrains.team.data.movies.searchMovies.service.model.ResponseSearchMovies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchMoviesApi {
    @GET("/3/search/movie?")
    fun getSearchMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int?

    ): Single<ResponseSearchMovies>
}