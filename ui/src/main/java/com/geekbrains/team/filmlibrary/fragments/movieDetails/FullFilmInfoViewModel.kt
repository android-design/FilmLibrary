package com.geekbrains.team.filmlibrary.fragments.movieDetails

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.movieDetails.interactor.GetMovieDetailsUseCase
import com.geekbrains.team.domain.movies.similarMovie.interactor.GetSimilarMoviesUseCase
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.model.ActorView
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.toActorView
import com.geekbrains.team.filmlibrary.model.toMovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class FullFilmInfoViewModel @Inject constructor(
    private val useCaseMovieInfo: GetMovieDetailsUseCase,
    private val useCaseSimilarMovies: GetSimilarMoviesUseCase
) : BaseViewModel() {

    val movieDetailsLiveData: MutableLiveData<MovieView> = MutableLiveData()
    val actorsLiveData: MutableLiveData<List<ActorView>> = MutableLiveData()
    val crewLiveData: MutableLiveData<List<ActorView>> = MutableLiveData()
    val similarMoviesLiveData:MutableLiveData<List<MovieView>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun loadMovieInfo(id: Int) {
        useCaseMovieInfo.execute(params = GetMovieDetailsUseCase.Params(id = id))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadMovieDetails, ::handleFailure)

    }

    @SuppressLint("CheckResult")
    fun loadSimilarMovies(id: Int, page: Int) {
        useCaseSimilarMovies.execute(GetSimilarMoviesUseCase.Param(id, page)).subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadSimilarMovies, ::handleFailure)
    }

    private fun handleOnSuccessLoadMovieDetails(details: Movie) {
        movieDetailsLiveData.value = details.toMovieView()
        actorsLiveData.value = details.cast?.map { it.toActorView() }
        crewLiveData.value = details.crew?.map { it.toActorView() }
    }

    private fun handleOnSuccessLoadSimilarMovies(movies: List<Movie>) {
        similarMoviesLiveData.value = movies.map { it.toMovieView() }
    }

}