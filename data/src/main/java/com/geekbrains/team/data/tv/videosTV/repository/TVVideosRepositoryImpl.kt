package com.geekbrains.team.data.tv.videosTV.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.common.videos.model.toVideo
import com.geekbrains.team.data.tv.videosTV.service.VideosTVApi
import com.geekbrains.team.domain.base.model.Video
import com.geekbrains.team.domain.movies.commonRepository.VideosRepository
import io.reactivex.Single
import javax.inject.Inject

class TVVideosRepositoryImpl @Inject constructor(private val api: VideosTVApi) :
    VideosRepository {

    override fun fetch(id: Int): Single<List<Video>> =
        api.getTVVideos(apiKey = BuildConfig.API_KEY, id = id)
            .map { response -> response.toVideo() }
}