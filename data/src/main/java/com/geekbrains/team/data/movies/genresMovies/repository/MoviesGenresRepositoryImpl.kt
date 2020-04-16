package com.geekbrains.team.data.movies.genresMovies.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const.LANGUAGE
import com.geekbrains.team.data.movies.genresMovies.service.GenresMoviesApi
import com.geekbrains.team.data.common.genre.model.toGenres
import com.geekbrains.team.domain.movies.commonRepository.MoviesGenresRepository
import com.geekbrains.team.domain.base.model.Genre
import io.reactivex.Single
import javax.inject.Inject

class MoviesGenresRepositoryImpl @Inject constructor(private val api: GenresMoviesApi) :
    MoviesGenresRepository {

    override fun fetch(): Single<List<Genre>> =
        api.getGenres(apiKey = BuildConfig.API_KEY, language = LANGUAGE)
            .map { response -> response.toGenres() }
}