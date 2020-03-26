package com.geekbrains.team.data.movies.upcomingMovies.repository

import com.geekbrains.team.data.Const.API_KEY
import com.geekbrains.team.data.Const.LANGUAGE
import com.geekbrains.team.data.movies.upcomingMovies.service.TopMoviesApi
import com.geekbrains.team.data.movies.upcomingMovies.service.model.toTopRatedMovie
import com.geekbrains.team.domain.movies.topRatedMovies.model.TopRatedMovie
import com.geekbrains.team.domain.movies.topRatedMovies.repository.TopMoviesRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopMoviesRepositoryImpl(val topMoviesApi: TopMoviesApi): TopMoviesRepository {

    private val api: TopMoviesApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(TopMoviesApi::class.java)

    override fun fetch(page: Int): Single<List<TopRatedMovie>> {
        return api.getTopMovies(apiKey = API_KEY, language = LANGUAGE, page = page).map { response ->
            response.toTopRatedMovie()
        }
    }

}