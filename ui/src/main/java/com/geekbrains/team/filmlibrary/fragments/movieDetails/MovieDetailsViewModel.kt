package com.geekbrains.team.filmlibrary.fragments.movieDetails

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.movieDetails.interactor.GetMovieDetailsUseCase
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val useCase: GetMovieDetailsUseCase) : BaseViewModel() {

    val movieDetailsLiveData = MutableLiveData<>()
}

/*
class TopViewModel @Inject constructor(
    private val useCaseTopRatedMovies: GetTopRatedMovies,
    private val useCaseTopRatedTVShows: GetTopRatedMovies
) :
    BaseViewModel() {
    var topRatedMoviesData: MutableLiveData<List<MovieView>> = MutableLiveData()
//    var topRatedTVShowsData: MutableLiveData<List<TVShowView>> = MutableLiveData()

    fun loadTopRatedMovies(page: Int) =
        useCaseTopRatedMovies.execute(params = GetTopRatedMovies.Params(page = page))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadTopRatedMovies, ::handleFailure)

    private fun handleOnSuccessLoadTopRatedMovies(list: List<Movie>) {
        topRatedMoviesData.value = list.map { it.toMovieView() }
    }

 */