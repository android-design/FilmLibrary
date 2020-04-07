package com.geekbrains.team.filmlibrary.fragments.mainScreen

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.upcomingMovies.interactor.GetUpcomingMovies
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.fragments.mainScreen.model.UpcomingMovieView
import com.geekbrains.team.filmlibrary.fragments.mainScreen.model.toUpcomingMovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(private val useCase: GetUpcomingMovies) :
    BaseViewModel() {
    var upcomingMoviesData: MutableLiveData<List<UpcomingMovieView>> = MutableLiveData()

    fun loadUpcomingMovies(page: Int) =
        useCase.execute(params = GetUpcomingMovies.Params(page = page))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadUpcomingMovies, ::handleFailure)


    private fun handleOnSuccessLoadUpcomingMovies(list: List<Movie>) {
        upcomingMoviesData.value = list.map { it.toUpcomingMovieView() }
    }
}