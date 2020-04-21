package com.geekbrains.team.data.tv.genresTV.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const.LANGUAGE
import com.geekbrains.team.data.common.genre.model.toGenres
import com.geekbrains.team.data.tv.genresTV.service.GenresTVApi
import com.geekbrains.team.domain.base.model.Genre
import com.geekbrains.team.domain.tv.commonRepository.TVGenresRepository
import io.reactivex.Single
import javax.inject.Inject

class TVGenresRepositoryImpl @Inject constructor(private val api: GenresTVApi) :
    TVGenresRepository {

    override fun fetch(): Single<List<Genre>> =
        api.getGenres(apiKey = BuildConfig.API_KEY, language = LANGUAGE)
            .map { response -> response.toGenres() }
}