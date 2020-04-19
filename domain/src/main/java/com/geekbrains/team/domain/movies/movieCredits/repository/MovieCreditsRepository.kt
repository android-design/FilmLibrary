package com.geekbrains.team.domain.movies.movieCredits.repository

import com.geekbrains.team.domain.movies.model.Credits
import io.reactivex.Single

interface MovieCreditsRepository {
    fun fetch(id: Int): Single<Credits>
}