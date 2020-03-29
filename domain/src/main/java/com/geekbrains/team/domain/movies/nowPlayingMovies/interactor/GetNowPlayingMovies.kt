package com.geekbrains.team.domain.movies.nowPlayingMovies.interactor

import com.geekbrains.team.domain.base.SingleUseCase
import com.geekbrains.team.domain.movies.nowPlayingMovies.model.NowPlayingMovies
import com.geekbrains.team.domain.movies.nowPlayingMovies.repository.NowPlayingMoviesRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetNowPlayingMovies @Inject constructor(private val repository: NowPlayingMoviesRepository) :
    SingleUseCase<List<NowPlayingMovies>, Int> {
    override fun execute(params: Int): Single<List<NowPlayingMovies>> {
        return repository.fetch(page = params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}