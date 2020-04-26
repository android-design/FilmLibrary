package com.geekbrains.team.data.movies.similarMovie.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.movies.similarMovie.service.SimilarMoviesApi
import com.geekbrains.team.data.movies.similarMovie.service.model.toMovieList
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.similarMovie.repository.SimilarMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class SimilarMoviesRepositoryImpl @Inject constructor(private val api: SimilarMoviesApi):
    SimilarMoviesRepository {

    override fun fetch(id: Int, page: Int): Single<List<Movie>> {
        return api.getSimilarMovies(apiKey = BuildConfig.API_KEY, language = Const.LANGUAGE,
                                page = page, id = id).map { response -> response.toMovieList() }
    }
}