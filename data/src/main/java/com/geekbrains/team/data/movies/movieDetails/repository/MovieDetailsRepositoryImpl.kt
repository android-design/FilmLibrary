package com.geekbrains.team.data.movies.movieDetails.repository

import com.geekbrains.team.data.BuildConfig
import com.geekbrains.team.data.Const
import com.geekbrains.team.data.movies.movieDetails.service.MovieDetailsApi
import com.geekbrains.team.data.movies.movieDetails.service.model.MovieDetailsResponse
import com.geekbrains.team.data.movies.movieDetails.service.model.toMovie
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
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
                api.getMovieDetails(BuildConfig.API_KEY, Const.LANGUAGE, t).
                observeOn(io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(object : DisposableSingleObserver<MovieDetailsResponse>() {
                    override fun onSuccess(movieResponse: MovieDetailsResponse) {
                        list.add(movieResponse.toMovie())
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }

            override fun onError(e: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return flowable
    }
}