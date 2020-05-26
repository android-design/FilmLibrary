package com.geekbrains.team.data.movies.topRatedMovies.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const.BASE_REGION
import com.geekbrains.team.data.Const.LANGUAGE
import com.geekbrains.team.data.movies.topRatedMovies.service.TopRatedMoviesApi
import com.geekbrains.team.data.movies.topRatedMovies.service.toMovie
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.topRatedMovies.repository.TopRatedMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class TopRatedMoviesRepositoryImpl @Inject constructor(private val api: TopRatedMoviesApi) :
    TopRatedMoviesRepository {

    override fun fetch(page: Int): Single<MutableList<Movie>> {
        return api.getTopMovies(
            apiKey = BuildConfig.API_KEY,
            language = LANGUAGE,
            page = page,
            region = BASE_REGION
        )
            .map { response ->
                response.toMovie()
            }
    }
}