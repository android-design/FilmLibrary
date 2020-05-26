package com.geekbrains.team.filmlibrary.fragments.top

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.topRatedMovies.interactor.GetTopRatedMovies
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.topRatedTV.interactor.GetTopRatedTV
import com.geekbrains.team.filmlibrary.addTo
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView
import com.geekbrains.team.filmlibrary.model.toMovieView
import com.geekbrains.team.filmlibrary.model.toTVShowView
import com.geekbrains.team.filmlibrary.util.LoadState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopViewModel @Inject constructor(
    private val useCaseTopRatedMovies: GetTopRatedMovies,
    private val useCaseTopRatedTVShows: GetTopRatedTV
) :
    BaseViewModel() {
    val topRatedMoviesData: MutableLiveData<List<MovieView>> = MutableLiveData()
    val topRatedTVShowsData: MutableLiveData<List<TVShowView>> = MutableLiveData()

    private var currentMoviePage = 1
    private var currentTVShowPage = 1

    private var isLoadingMoviesInProgress = false
    private var isLoadingTVShowInProgress = false

    var loadingMovieState = MutableLiveData<LoadState>()
    var loadingTVShowState = MutableLiveData<LoadState>()

    fun loadTopRatedMovies() {
        currentMoviePage = 1
        isLoadingMoviesInProgress = true

        useCaseTopRatedMovies.execute(params = GetTopRatedMovies.Params(page = currentMoviePage))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handleOnSuccessLoadTopRatedMovies(result = it) },
                ::handleFailureLoadTopRatedMovies
            )
            .addTo(compositeDisposable)
    }

    fun loadTopRatedMoviesMoore() {
        if (isLoadingMoviesInProgress) return
        isLoadingMoviesInProgress = true
        loadingMovieState.postValue(LoadState.Loading)

        useCaseTopRatedMovies.execute(
            params = GetTopRatedMovies.Params(
                page = ++currentMoviePage
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    handleOnSuccessLoadTopRatedMovies(
                        it, true
                    )
                }, { handleFailureLoadTopRatedMovies(it) }
            )
            .addTo(compositeDisposable)
    }

    private fun handleOnSuccessLoadTopRatedMovies(
        result: List<Movie>,
        isLoadMoore: Boolean = false
    ) {
        if (isLoadMoore && topRatedMoviesData.value != null) {
            loadingMovieState.postValue(LoadState.Done)

            topRatedMoviesData.value = topRatedMoviesData.value?.toMutableList().apply {
                this?.addAll(result.map {
                    it.toMovieView()
                })
            }
        } else {
            topRatedMoviesData.value = result.map {
                it.toMovieView()
            }
        }

        isLoadingMoviesInProgress = false
    }

    private fun handleFailureLoadTopRatedMovies(failure: Throwable) {
        handleFailure(failure)

        loadingMovieState.postValue(LoadState.Error(failure))
        currentMoviePage--
        isLoadingMoviesInProgress = false
    }

    fun loadTopRatedTVShows() {
        currentTVShowPage = 1
        isLoadingTVShowInProgress = true

        useCaseTopRatedTVShows.execute(params = GetTopRatedTV.Params(page = currentTVShowPage))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handleOnSuccessLoadTopRatedTVShows(result = it) },
                ::handleFailureLoadTopRatedTVShow
            )
            .addTo(compositeDisposable)
    }

    fun loadTopRatedTVShowMoore() {
        if (isLoadingTVShowInProgress) return
        isLoadingTVShowInProgress = true
        loadingTVShowState.postValue(LoadState.Loading)

        useCaseTopRatedTVShows.execute(
            params = GetTopRatedTV.Params(
                page = ++currentTVShowPage
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    handleOnSuccessLoadTopRatedTVShows(
                        it, true
                    )
                }, ::handleFailureLoadTopRatedTVShow
            )
            .addTo(compositeDisposable)
    }


    private fun handleOnSuccessLoadTopRatedTVShows(
        result: List<TVShow>,
        isLoadMoore: Boolean = false
    ) {
        if (isLoadMoore && topRatedTVShowsData.value != null) {
            loadingTVShowState.postValue(LoadState.Done)

            topRatedTVShowsData.value = topRatedTVShowsData.value?.toMutableList().apply {
                this?.addAll(result.map {
                    it.toTVShowView()
                })
            }
        } else {
            topRatedTVShowsData.value = result.map {
                it.toTVShowView()
            }
        }

        isLoadingTVShowInProgress = false
    }

    private fun handleFailureLoadTopRatedTVShow(failure: Throwable) {
        handleFailure(failure)

        loadingTVShowState.postValue(LoadState.Error(failure))
        currentTVShowPage--
        isLoadingTVShowInProgress = false
    }
}