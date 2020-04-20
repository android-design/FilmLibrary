package com.geekbrains.team.data.movies.imagesMovies.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.common.images.model.toImages
import com.geekbrains.team.data.movies.imagesMovies.service.ImagesMoviesApi
import com.geekbrains.team.domain.base.model.Images
import com.geekbrains.team.domain.movies.commonRepository.MoviesImagesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesImagesRepositoryImpl @Inject constructor(private val api: ImagesMoviesApi) :
    MoviesImagesRepository {

    override fun fetch(id: Int): Single<Images> =
        api.getMovieImages(apiKey = BuildConfig.API_KEY, id = id)
            .map { response -> response.toImages() }
}