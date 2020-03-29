package com.geekbrains.team.data.movies.nowPlayingMovies.repository

import com.geekbrains.team.data.Const.API_KEY
import com.geekbrains.team.data.Const.LANGUAGE
import com.geekbrains.team.data.movies.nowPlayingMovies.service.NowPlayingMoviesApi
import com.geekbrains.team.data.movies.nowPlayingMovies.service.model.toNowPlayingMovies
import com.geekbrains.team.domain.movies.nowPlayingMovies.model.NowPlayingMovies
import com.geekbrains.team.domain.movies.nowPlayingMovies.repository.NowPlayingMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class NowPlayingMoviesRepositoryImpl @Inject constructor(private val api: NowPlayingMoviesApi) :
    NowPlayingMoviesRepository {

    override fun fetch(page: Int): Single<List<NowPlayingMovies>> =
        api.getNowPlayingMovies(apiKey = API_KEY, language = LANGUAGE, page = page)
            .map { response ->  response.toNowPlayingMovies()}
}