package com.geekbrains.team.data.movies.movieDetails.repository

import com.geekbrains.team.data.movies.movieDetails.service.MovieDetailsApi
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(private val api: MovieDetailsApi): MovieDetailsRepository {
    override fun getMovie(id: Int): Single<Movie> {
        TODO("Not yet implemented")
    }

    override fun getMovieList(ids: List<Int>): Flowable<List<Movie>> {
        var list = mutableListOf<Movie>()
        val observable: Observable<Int> = Observable.fromIterable(ids)
        val flowable: Flowable<List<Movie>> = Flowable.just(list)
        observable.subscribe(object : Observer<Int> {
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {
                TODO("Not yet implemented")
            }

            override fun onNext(t: Int) {

            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return flowable
    }
}