package com.geekbrains.team.filmlibrary.movies.nowPlayingMovies.viewModel

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.nowPlayingMovies.interactor.GetNowPlayingMovies
import com.geekbrains.team.domain.movies.nowPlayingMovies.model.NowPlayingMovies
import com.geekbrains.team.domain.movies.upcomingMovies.interactor.GetUpcomingMovies
import com.geekbrains.team.domain.movies.upcomingMovies.model.UpcomingMovie
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.movies.nowPlayingMovies.model.NowPlayingMovieView
import com.geekbrains.team.filmlibrary.movies.nowPlayingMovies.model.toNowPlayingMovieView
import com.geekbrains.team.filmlibrary.movies.upcomingMovies.model.UpcomingMovieView
import com.geekbrains.team.filmlibrary.movies.upcomingMovies.model.toUpcomingMovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NowPlayingMoviesViewModel @Inject constructor(private val useCase: GetNowPlayingMovies) :
    BaseViewModel() {
    var data: MutableLiveData<List<NowPlayingMovieView>> = MutableLiveData()

    fun loadNowPlayingMovies(page: Int) =
        useCase.execute(params = page)
            .subscribe(::handleMovieList, ::handleFailure)

    private fun handleMovieList(list: List<NowPlayingMovies>) {
        data.value = list.map { it.toNowPlayingMovieView() }
    }
}