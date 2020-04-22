package com.geekbrains.team.filmlibrary.fragments.movieDetails

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.model.MovieDetails
import com.geekbrains.team.domain.movies.movieDetails.interactor.GetMovieDetailsUseCase
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val useCase: GetMovieDetailsUseCase
) : BaseViewModel() {

    val movieDetailsLiveData = MutableLiveData<MovieDetails>()

    fun loadTopRatedMovies(id: Int) {
        useCase.execute(GetMovieDetailsUseCase.Params(id)).subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadMovieDetails, ::handleFailure)
    }

    private fun handleOnSuccessLoadMovieDetails(details: MovieDetails) {
        movieDetailsLiveData.value = details
    }

}