package com.geekbrains.team.data.movies.topRatedMovies.repository

import com.geekbrains.team.data.Const.LANGUAGE
import com.geekbrains.team.data.Key.API_KEY
import com.geekbrains.team.data.movies.topRatedMovies.service.TopRatedMoviesApi
import com.geekbrains.team.data.movies.topRatedMovies.service.toMovie
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.topRatedMovies.repository.TopMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class TopRatedMoviesRepositoryImpl @Inject constructor(private val api: TopRatedMoviesApi) :
    TopMoviesRepository {

    override fun fetch(page: Int): Single<List<Movie>> {
        return api.getTopMovies(apiKey = API_KEY, language = LANGUAGE, page = page)
            .map { response ->
                response.toMovie()
            }
    }
}