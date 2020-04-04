package com.geekbrains.team.filmlibrary.movies.upcomingMovies.viewModel

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.upcomingMovies.interactor.GetUpcomingMovies
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.movies.upcomingMovies.model.UpcomingMovieView
import com.geekbrains.team.filmlibrary.movies.upcomingMovies.model.toUpcomingMovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpcomingMoviesViewModel @Inject constructor(private val useCase: GetUpcomingMovies) :
    BaseViewModel() {
    var data: MutableLiveData<List<UpcomingMovieView>> = MutableLiveData()

    fun loadUpcomingMovies(page: Int) =
        useCase.execute(params = GetUpcomingMovies.Params(page = page))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccess, ::handleFailure)


    private fun handleOnSuccess(list: List<Movie>) {
        data.value = list.map { it.toUpcomingMovieView() }
    }
}