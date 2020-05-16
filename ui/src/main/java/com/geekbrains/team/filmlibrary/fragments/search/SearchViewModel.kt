package com.geekbrains.team.filmlibrary.fragments.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.searchMovies.interactor.GetSearchedMovies
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.searchTV.interactor.GetSearchedTV
import com.geekbrains.team.filmlibrary.addTo
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView
import com.geekbrains.team.filmlibrary.model.toMovieView
import com.geekbrains.team.filmlibrary.model.toTVShowView
import com.geekbrains.team.filmlibrary.util.LoadState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val useCaseSearchedMovies: GetSearchedMovies,
    private val useCaseSearchedTV: GetSearchedTV
) : BaseViewModel() {

    var searchedMoviesData: MutableLiveData<List<MovieView>> = MutableLiveData()
    var searchedTVData: MutableLiveData<List<TVShowView>> = MutableLiveData()

    var currentQuery = ""

    val newMoviePage
        get() = currentMoviePage != 1
    var currentMoviePage = 1

    val newTVPage
        get() = currentTVPage != 1
    var currentTVPage = 1
    var loadingMovieState = MutableLiveData<LoadState>()
    var loadingTVState = MutableLiveData<LoadState>()

    fun loadSearchedMovies(query: String, page: Int? = null) {
        loadingMovieState.value = LoadState.Loading

        useCaseSearchedMovies.execute(
            params = GetSearchedMovies.Params(
                query = query,
                page = page
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadSearchedMovies, ::handleFailureLoadSearchedMovies)
            .addTo(compositeDisposable)
    }


    private fun handleOnSuccessLoadSearchedMovies(result: List<Movie>) {
        searchedMoviesData.value = result.map {
            it.toMovieView()
        }
        loadingMovieState.value = LoadState.Done
    }

    private fun handleFailureLoadSearchedMovies(failure: Throwable) {
        handleFailure(failure)

        loadingMovieState.value = LoadState.Error(failure)
    }

    fun loadSearchedTV(query: String, page: Int? = null) {
        loadingTVState.value = LoadState.Loading
        useCaseSearchedTV.execute(
            params = GetSearchedTV.Params(
                query = query,
                page = page
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadSearchedTV, ::handleFailureLoadLoadSearchedTV)
            .addTo(compositeDisposable)
    }

    private fun handleOnSuccessLoadSearchedTV(result: List<TVShow>) {
        searchedTVData.value = result.map {
            it.toTVShowView()
        }
        loadingTVState.value = LoadState.Done
    }

    private fun handleFailureLoadLoadSearchedTV(failure: Throwable) {
        handleFailure(failure)

        loadingTVState.value = LoadState.Error(failure)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + 5 >= totalItemCount) {

            loadingTVState.postValue(LoadState.Loading)
            loadSearchedTV(currentQuery, ++currentTVPage)
        }
    }
}