package com.geekbrains.team.data.movies.searchMovies.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.movies.searchMovies.service.SearchMoviesApi
import com.geekbrains.team.data.movies.searchMovies.service.model.toSearchMovie
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.searchMovies.repository.SearchMoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchMoviesRepositoryImpl @Inject constructor(private val api: SearchMoviesApi) :
    SearchMoviesRepository {
    override fun fetch(query: String, page: Int?)
            : Single<List<Movie>> {
        return api.getSearchMovies(
            apiKey = BuildConfig.API_KEY,
            language = Const.LANGUAGE,
            query = query,
            page = page
        )
            .map { response ->
                response.toSearchMovie()
            }
    }
}