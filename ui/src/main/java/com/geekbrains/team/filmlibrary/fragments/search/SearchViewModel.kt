package com.geekbrains.team.filmlibrary.fragments.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.searchMovies.interactor.GetSearchedMovies
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.searchTV.interactor.GetSearchedTV
import com.geekbrains.team.filmlibrary.Const.PRELOAD_FROM_SERVER_ITEMS_COUNT
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

class SearchViewModel @Inject constructor(
    private val useCaseSearchedMovies: GetSearchedMovies,
    private val useCaseSearchedTV: GetSearchedTV
) : BaseViewModel() {

    var searchedMoviesData: MutableLiveData<List<MovieView>> = MutableLiveData()
    var searchedTVData: MutableLiveData<List<TVShowView>> = MutableLiveData()

    var currentQuery = ""

    val isFirstMoviePage
        get() = currentMoviePage == 1
    private var currentMoviePage = 1
    var loadingMovieState = MutableLiveData<LoadState>()

    val isFirstTVShowPage
        get() = currentTVPage == 1
    private var currentTVPage = 1
    var loadingTVState = MutableLiveData<LoadState>()

    // Movies

    fun loadSearchedMovies() {
        currentMoviePage = 1

        useCaseSearchedMovies.execute(
            params = GetSearchedMovies.Params(
                query = currentQuery
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    handleOnSuccessLoadSearchedMovies(
                        it
                    )
                }, { handleFailureLoadSearchedMovies(failure = it) }
            )
            .addTo(compositeDisposable)
    }

    private fun handleOnSuccessLoadSearchedMovies(
        result: List<Movie>,
        isLoadMoore: Boolean = false
    ) {

        if (isLoadMoore) {
            loadingMovieState.value = LoadState.Done

            searchedMoviesData.value = searchedMoviesData.value?.toMutableList().apply {
                this?.addAll(result.map {
                    it.toMovieView()
                })
            }
        } else {
            searchedMoviesData.value = result.map {
                it.toMovieView()
            }
        }
    }

    private fun handleFailureLoadSearchedMovies(failure: Throwable, isLoadMoore: Boolean = false) {
        handleFailure(failure)

        if (isLoadMoore) {
            loadingMovieState.value = LoadState.Error(failure)
            currentMoviePage--
        }
    }

    fun loadSearchedMoviesMoore() {
        loadingMovieState.value = LoadState.Loading

        useCaseSearchedMovies.execute(
            params = GetSearchedMovies.Params(
                query = currentQuery,
                page = ++currentMoviePage
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    handleOnSuccessLoadSearchedMovies(
                        it, true
                    )
                }, { handleFailureLoadSearchedMovies(it, true) }
            )
            .addTo(compositeDisposable)
    }

    // TV Show

    fun loadSearchedTV() {
        currentTVPage = 1

        useCaseSearchedTV.execute(
            params = GetSearchedTV.Params(
                query = currentQuery
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handleOnSuccessLoadSearchedTV(it) },
                { handleFailureLoadLoadSearchedTV(it) })
            .addTo(compositeDisposable)
    }

    fun loadSearchedTVMoore() {
        loadingTVState.postValue(LoadState.Loading)

        useCaseSearchedTV.execute(
            params = GetSearchedTV.Params(
                query = currentQuery,
                page = ++currentTVPage
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { handleOnSuccessLoadSearchedTV(it, true) },
                { handleFailureLoadLoadSearchedTV(it, true) })
            .addTo(compositeDisposable)
    }

    private fun handleOnSuccessLoadSearchedTV(result: List<TVShow>, isLoadMoore: Boolean = false) {
        if (isLoadMoore) {
            loadingTVState.value = LoadState.Done

            searchedTVData.value = searchedTVData.value?.toMutableList().apply {
                this?.addAll(result.map {
                    it.toTVShowView()
                })
            }
        } else {
            searchedTVData.value = result.map {
                it.toTVShowView()
            }
        }
    }

    private fun handleFailureLoadLoadSearchedTV(failure: Throwable, isLoadMoore: Boolean = false) {
        handleFailure(failure)

        if (isLoadMoore) {
            loadingTVState.value = LoadState.Error(failure)
            currentTVPage--
        }
    }

    fun listScrolled(
        visibleItemCount: Int,
        lastVisibleItemPosition: Int,
        totalItemCount: Int,
        callBack: () -> Unit
    ) {
        if (visibleItemCount + lastVisibleItemPosition + PRELOAD_FROM_SERVER_ITEMS_COUNT >= totalItemCount) {
            callBack.invoke()
        }
    }
}