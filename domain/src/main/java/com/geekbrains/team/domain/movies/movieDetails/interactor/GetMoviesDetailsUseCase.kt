package com.geekbrains.team.domain.movies.movieDetails.interactor

import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.base.UseCaseFlowable
import com.geekbrains.team.domain.movies.favoriteMovie.repository.FavoriteMoviesRepository
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.movieDetails.repository.MovieDetailsRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.single.SingleZipIterable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class GetMoviesDetailsUseCase @Inject constructor(private val detailsRepository: MovieDetailsRepository,
private val idsRepository: FavoriteMoviesRepository): UseCaseFlowable<List<Movie>, List<Int>> {
    override fun execute(params: List<Int>): Flowable<List<Movie>> {
        val list = mutableListOf<Movie>()
        idsRepository.getFavoriteMoviesIds().subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Int>>() {
                override fun onSuccess(result: List<Int>) {
                    Flowable.fromIterable(result).subscribe{
                        detailsRepository.getMovie(it).subscribeOn(io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : DisposableSingleObserver<Movie>(){
                                override fun onSuccess(t: Movie) {
                                    list.add(t)
                                }

                                override fun onError(e: Throwable) {
                                    throw e
                                }

                            })
                    }
                }

                override fun onError(e: Throwable) {

                }


            })
        return Flowable.just(list)
    }
}