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
        return api.getMovieDetails(BuildConfig.API_KEY, Const.LANGUAGE, id).map { movieDetailsResponse -> movieDetailsResponse.toMovie() }
    }
}
/*
    Использовать функцию zip()
 */