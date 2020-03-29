package com.geekbrains.team.domain.movies.nowPlayingMovies.repository

import com.geekbrains.team.domain.movies.nowPlayingMovies.model.NowPlayingMovies
import io.reactivex.Single

interface NowPlayingMoviesRepository {
    fun fetch(page: Int): Single<List<NowPlayingMovies>>
}