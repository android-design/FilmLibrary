package com.geekbrains.team.data.movies.nowPlayingMovies.repository

import com.geekbrains.team.data.Const.API_KEY
import com.geekbrains.team.data.Const.LANGUAGE
import com.geekbrains.team.data.movies.nowPlayingMovies.service.NowPlayingMoviesApi
import com.geekbrains.team.data.movies.upcomingMovies.service.UpcomingMoviesApi
import com.geekbrains.team.data.movies.upcomingMovies.service.model.toUpcomingMovie
import com.geekbrains.team.domain.movies.upcomingMovies.model.UpcomingMovie
import com.geekbrains.team.domain.movies.upcomingMovies.repository.UpcomingMoviesRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NowPlayingMoviesRepositoryImpl() : UpcomingMoviesRepository {
    // TODO Move do dagger
    private val api: NowPlayingMoviesApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NowPlayingMoviesApi::class.java)

    override fun fetch(page: Int): Single<List<UpcomingMovie>> {
        TODO("Not yet implemented")
    }

//    override fun fetch(page: Int): Single<List<UpcomingMovie>> =
//        api.getUpcomingMovies(apiKey = API_KEY, language = LANGUAGE, page = page)
//            .map { response ->
//                response.toUpcomingMovie()
//            }
}