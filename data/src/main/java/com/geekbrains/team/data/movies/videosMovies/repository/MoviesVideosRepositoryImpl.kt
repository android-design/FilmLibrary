package com.geekbrains.team.data.movies.videosMovies.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.common.videos.model.toVideo
import com.geekbrains.team.data.movies.videosMovies.service.VideosMoviesApi
import com.geekbrains.team.domain.base.model.Video
import com.geekbrains.team.domain.movies.commonRepository.VideosRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesVideosRepositoryImpl @Inject constructor(private val api: VideosMoviesApi) :
    VideosRepository {

    override fun fetch(id: Int): Single<List<Video>> =
        api.getMoviesVideos(apiKey = BuildConfig.API_KEY, id = id)
            .map { response -> response.toVideo() }
}