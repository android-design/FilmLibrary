package com.geekbrains.team.filmlibrary.movies.upcomingMovies.viewModel

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.upcomingMovies.interactor.GetUpcomingMovies
import com.geekbrains.team.domain.movies.upcomingMovies.model.UpcomingMovie
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.movies.upcomingMovies.model.UpcomingMovieView
import com.geekbrains.team.filmlibrary.movies.upcomingMovies.model.toUpcomingMovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpcomingMoviesViewModel @Inject constructor(private val useCase: GetUpcomingMovies) : BaseViewModel() {
    var data: MutableLiveData<List<UpcomingMovieView>> = MutableLiveData()

    fun loadUpcomingMovies(page: Int) =
        useCase.execute(params = page)
            .subscribe(::handleMovieList, ::handleFailure)

    private fun handleMovieList(list: List<UpcomingMovie>) {
        data.value = list.map { it.toUpcomingMovieView() }
    }
}