package com.geekbrains.team.data.movies.upcomingMovies.service

import com.geekbrains.team.data.movies.upcomingMovies.service.model.ResponseTopRatedMovies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TopMoviesApi {
    @GET()
    fun getTopMovies(@Path("language") language: String): Single<ResponseTopRatedMovies>
}