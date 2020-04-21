package com.geekbrains.team.filmlibrary.fragments.top

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.topRatedMovies.interactor.GetTopRatedMovies
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.toMovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopTVShowViewModel @Inject constructor(
    private val useCaseTopRatedMovies: GetTopRatedMovies
) :
    BaseViewModel() {
    var topRatedMoviesData: MutableLiveData<List<MovieView>> = MutableLiveData()

    fun loadTopRatedMovies() =
        useCaseTopRatedMovies.execute(params = None())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadTopRatedMovies, ::handleFailure)

    private fun handleOnSuccessLoadTopRatedMovies(list: List<Movie>) {
        topRatedMoviesData.value = list.map { it.toMovieView() }
    }
}