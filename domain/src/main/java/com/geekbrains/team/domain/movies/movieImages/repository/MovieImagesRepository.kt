package com.geekbrains.team.domain.movies.movieImages.repository

import com.geekbrains.team.domain.movies.model.Movie
import io.reactivex.Single

interface MovieImagesRepository {

    fun fetch(id: Int): Single<List<String>>

}