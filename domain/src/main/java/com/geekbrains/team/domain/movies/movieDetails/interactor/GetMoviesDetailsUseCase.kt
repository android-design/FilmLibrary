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


class GetMoviesDetailsUseCase @Inject constructor(private val repository: MovieDetailsRepository): UseCase<Movie, Int> {
    override fun execute(params: Int): Single<Movie> {
        return repository.getMovie(params)
    }


}