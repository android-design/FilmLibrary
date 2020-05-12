package com.geekbrains.team.data.tv.images.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.common.images.model.toImages
import com.geekbrains.team.data.tv.images.service.TVImagesApi
import com.geekbrains.team.domain.base.model.Images
import com.geekbrains.team.domain.tv.commonRepository.TVImagesRepository
import io.reactivex.Single
import javax.inject.Inject

class TVImagesRepositoryImpl @Inject constructor(
    private val api: TVImagesApi
): TVImagesRepository{
    override fun fetch(id: Int): Single<Images> {
        return api.getMovieImages(id = id, apiKey = BuildConfig.API_KEY).map { it.toImages() }
    }
}