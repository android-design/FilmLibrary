package com.geekbrains.team.filmlibrary.fragments.search

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.searchMovies.interactor.GetSearchedMovies
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.searchTVShow.interactor.GetSearchedTVShow
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.fragments.search.model.SearchedMovieView
import com.geekbrains.team.filmlibrary.fragments.search.model.SearchedTVShowView
import com.geekbrains.team.filmlibrary.fragments.search.model.toSearchedMovieView
import com.geekbrains.team.filmlibrary.fragments.search.model.toSearchedTVShowView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val useCaseSearchedMovies: GetSearchedMovies,
    private val useCaseSearchedTVShow: GetSearchedTVShow
) :
    BaseViewModel() {
    var currentReleaseYear: Int = 0

    var searchedMoviesData: MutableLiveData<List<SearchedMovieView>> = MutableLiveData()
    var searchedTVShowsData: MutableLiveData<List<SearchedTVShowView>> = MutableLiveData()

    fun loadSearchedMovies(query: String, page: Int) =
        useCaseSearchedMovies.execute(
            params = GetSearchedMovies.Params(
                query = query,
                releaseYear = currentReleaseYear,
                page = page
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadSearchedMovies, ::handleFailure)

    private fun handleOnSuccessLoadSearchedMovies(list: List<Movie>) {
        searchedMoviesData.value = list.map { it.toSearchedMovieView() }
    }

    fun loadSearchedTVShows(query: String, page: Int) =
        useCaseSearchedTVShow.execute(
            params = GetSearchedTVShow.Params(
                query = query,
                page = page
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadSearchedTVShows, ::handleFailure)

    private fun handleOnSuccessLoadSearchedTVShows(list: List<TVShow>) {
        searchedTVShowsData.value = list.map { it.toSearchedTVShowView() }
    }
}