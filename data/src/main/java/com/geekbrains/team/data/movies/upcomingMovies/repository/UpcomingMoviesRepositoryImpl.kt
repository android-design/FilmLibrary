package com.geekbrains.team.data.movies.upcomingMovies.repository

import com.geekbrains.team.data.Const.API_KEY
import com.geekbrains.team.data.Const.LANGUAGE
import com.geekbrains.team.data.movies.upcomingMovies.service.UpcomingMoviesApi
import com.geekbrains.team.data.movies.upcomingMovies.service.model.toUpcomingMovie
import com.geekbrains.team.domain.movies.upcomingMovies.model.UpcomingMovie
import com.geekbrains.team.domain.movies.upcomingMovies.repository.UpcomingMoviesRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpcomingMoviesRepositoryImpl() : UpcomingMoviesRepository {
    // TODO Move do dagger
    private val api: UpcomingMoviesApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UpcomingMoviesApi::class.java)

    override fun fetch(page: Int): Single<List<UpcomingMovie>> =
        api.getUpcomingMovies(apiKey = API_KEY, language = LANGUAGE, page = page)
            .map { response ->
                response.toUpcomingMovie()
            }
}