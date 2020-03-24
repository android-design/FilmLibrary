package com.geekbrains.team.data.movies.upcomingMovies.repository

import com.geekbrains.team.data.movies.upcomingMovies.service.UpcomingMoviesApi
import com.geekbrains.team.domain.model.Movie
import com.geekbrains.team.domain.repository.TopMoviesRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.geekbrains.team.data.movies.upcomingMovies.service.model.ResponseTopRatedMovies
import com.geekbrains.team.data.movies.upcomingMovies.service.TopMoviesApi
import io.reactivex.Single
import io.reactivex.functions.Function
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopMoviesRepositoryImpl(val topMoviesApi: TopMoviesApi): TopMoviesRepository {

    private val api: UpcomingMoviesApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UpcomingMoviesApi::class.java)

    override fun getTopMovies(language: String): Single<List<Movie>> {
        return topMoviesApi.getTopMovies(language).map { object: Function<ResponseTopRatedMovies, Movie> {
            override fun apply(t: ResponseTopRatedMovies): Movie {
                return
            }

        } }
    }
}