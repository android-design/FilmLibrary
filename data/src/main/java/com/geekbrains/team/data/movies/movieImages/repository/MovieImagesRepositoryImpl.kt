package com.geekbrains.team.data.movies.movieImages.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.movies.movieImages.service.MovieImagesApi
import com.geekbrains.team.data.movies.movieImages.service.model.MovieImagesResponse
import com.geekbrains.team.data.movies.movieImages.service.model.toPostersList
import com.geekbrains.team.domain.movies.commonRepository.MovieImagesRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieImagesRepositoryImpl @Inject constructor(private val api: MovieImagesApi) :
    MovieImagesRepository {
    override fun fetch(id: Int): Single<List<String>> {
        return api.getMovieImages(BuildConfig.API_KEY, Const.LANGUAGE, id)
            .map { response: MovieImagesResponse ->
                response.toPostersList()
            }
    }
}