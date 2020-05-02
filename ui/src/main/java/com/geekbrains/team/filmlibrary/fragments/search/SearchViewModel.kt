package com.geekbrains.team.filmlibrary.fragments.search

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.base.model.MovieAndTVShow
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.search.interactor.GetSearchedResult
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.fragments.search.model.SearchView
import com.geekbrains.team.filmlibrary.fragments.search.model.toSearchedMovieView
import com.geekbrains.team.filmlibrary.fragments.search.model.toSearchedTVShowView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val useCaseSearchedResult: GetSearchedResult
) :
    BaseViewModel() {

    var currentReleaseYear: Int? = null
    var isNeedSearchMovies: Boolean = true
    var isNeedSearchTVShows: Boolean = true

    var searchedMoviesData: MutableLiveData<List<SearchView>> = MutableLiveData()

    fun loadSearchedMovies(query: String, page: Int) {
        val disposable = useCaseSearchedResult.execute(
            params = GetSearchedResult.Params(
                query = query,
                releaseYear = currentReleaseYear,
                page = page,
                isNeedSearchMovies = isNeedSearchMovies,
                isNeedSearchTVShows = isNeedSearchTVShows
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadSearchedMovies, ::handleFailure)

        addDisposable(disposable)
    }


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