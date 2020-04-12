package com.geekbrains.team.filmlibrary.fragments.search

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.base.model.MovieAndTVShow
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.search.interactor.GetSearchedResult
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.searchTVShow.interactor.GetSearchedTVShow
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.fragments.search.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val useCaseSearchedResult: GetSearchedResult,
    private val useCaseSearchedTVShow: GetSearchedTVShow
) :
    BaseViewModel() {
    var currentReleaseYear: Int? = null
    var isNeedSearchMovies: Boolean = true
    var isNeedSearchTVShows: Boolean = true

    var searchedMoviesData: MutableLiveData<List<SearchView>> = MutableLiveData()

    fun loadSearchedMovies(query: String, page: Int) =
        useCaseSearchedResult.execute(
            params = GetSearchedResult.Params(
                query = query,
                releaseYear = currentReleaseYear,
                page = page
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadSearchedMovies, ::handleFailure)

    private fun handleOnSuccessLoadSearchedMovies(list: List<MovieAndTVShow>) {
        searchedMoviesData.value = list.map {
            when (it) {
                is Movie -> it.toSearchedMovieView()
                is TVShow -> it.toSearchedTVShowView()
                else -> throw Throwable("Ooops")
            }
        }
    }
}