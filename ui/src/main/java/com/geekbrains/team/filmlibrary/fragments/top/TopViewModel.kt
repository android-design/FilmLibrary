package com.geekbrains.team.filmlibrary.fragments.top

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.topRatedMovies.interactor.GetTopRatedMovies
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.TVShowView
import com.geekbrains.team.filmlibrary.model.toMovieView
import com.geekbrains.team.filmlibrary.model.toTVShowView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//todo здесь нужно заменить GetTopRatedMovies в типе useCaseTopRatedTVShows на запрос топа сериалов с сервера
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

//    fun loadTopRatedTVShows(page: Int) =
//        useCaseTopRatedTVShows.execute(params = GetTopRatedMovies.Params(page = page))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(::handleOnSuccessLoadTopRatedTVShows, ::handleFailure)
//
//    private fun handleOnSuccessLoadTopRatedTVShows(list: List<TVShow>) {
//        topRatedTVShowsData.value = list.map { it.toTVShowView() }
//    }
}