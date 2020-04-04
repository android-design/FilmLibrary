package com.geekbrains.team.filmlibrary.movies.nowPlayingMovies.viewModel

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.nowPlayingMovies.interactor.GetNowPlayingMovies
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.movies.nowPlayingMovies.model.NowPlayingMovieView
import com.geekbrains.team.filmlibrary.movies.nowPlayingMovies.model.toMovieView
import javax.inject.Inject

class NowPlayingMoviesViewModel @Inject constructor(private val useCase: GetNowPlayingMovies) :
    BaseViewModel() {
    var data: MutableLiveData<List<NowPlayingMovieView>> = MutableLiveData()

    fun loadNowPlayingMovies(page: Int) =
        useCase.execute(params = GetNowPlayingMovies.Params(page = page))
            .subscribe(::handleMovieList, ::handleFailure)

    private fun handleMovieList(list: List<Movie>) {
        data.value = list.map { it.toMovieView() }
    }
}