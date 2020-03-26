package com.geekbrains.team.data.movies.upcomingMovies.service

import io.reactivex.Single
import retrofit2.http.GET

interface GenresApi {
    @GET()
    fun getGenres(language: String): Single<>
}