package com.geekbrains.team.domain.movies.commonRepository

import com.geekbrains.team.domain.base.model.Genre
import io.reactivex.Single

interface MoviesGenresRepository {
    fun fetch(): Single<List<Genre>>
}