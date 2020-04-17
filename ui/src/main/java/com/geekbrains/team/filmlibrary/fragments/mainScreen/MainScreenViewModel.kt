package com.geekbrains.team.filmlibrary.fragments.mainScreen

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.nowPlayingMovies.interactor.GetFirstNowPlayingMovie
import com.geekbrains.team.domain.movies.nowPlayingMovies.interactor.GetNowPlayingMovies
import com.geekbrains.team.domain.movies.topRatedMovies.interactor.GetRandomTopRatedMovie
import com.geekbrains.team.domain.movies.upcomingMovies.interactor.GetUpcomingMovies
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.fragments.mainScreen.model.NowPlayingMovieView
import com.geekbrains.team.filmlibrary.fragments.mainScreen.model.UpcomingMovieView
import com.geekbrains.team.filmlibrary.fragments.mainScreen.model.toNowPlayingMovieView
import com.geekbrains.team.filmlibrary.fragments.mainScreen.model.toUpcomingMovieView
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.toMovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val useCaseUpcomingMovies: GetUpcomingMovies,
    private val useCaseNowPlayingMovies: GetNowPlayingMovies,
    private val useCaseRandomTopRatedMovie: GetRandomTopRatedMovie,
    private val useCaseFirstNowPlayingMovie: GetFirstNowPlayingMovie
) :
    BaseViewModel() {
    val nowPlayingMoviesData: MutableLiveData<List<NowPlayingMovieView>> = MutableLiveData()
    val upcomingMoviesData: MutableLiveData<List<UpcomingMovieView>> = MutableLiveData()
    val randomTopRatedMovieData: MutableLiveData<MovieView> = MutableLiveData()

    //todo изменить на правильное отображение для двух нижних карточек
    val movieOfTheWeek: MutableLiveData<NowPlayingMovieView> = MutableLiveData()
    val tvShowPremier: MutableLiveData<NowPlayingMovieView> = MutableLiveData()

    fun loadNowPlayingMovies(page: Int) =
        useCaseNowPlayingMovies.execute(params = GetNowPlayingMovies.Params(page = page))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadNowPlayingMovies, ::handleFailure)

    private fun handleOnSuccessLoadNowPlayingMovies(list: List<Movie>) {
        nowPlayingMoviesData.value = list.map { it.toNowPlayingMovieView() }
    }

    fun loadUpcomingMovies(page: Int) =
        useCaseUpcomingMovies.execute(params = GetUpcomingMovies.Params(page = page))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadUpcomingMovies, ::handleFailure)

    private fun handleOnSuccessLoadUpcomingMovies(list: List<Movie>) {
        upcomingMoviesData.value = list.map { it.toUpcomingMovieView() }
    }

    fun loadRandomTopRatedMovie() =
        useCaseRandomTopRatedMovie.execute(None())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadRandomTopRatedMovie, ::handleFailure)

    //берём рандомнуый фильм из страницы (для большего рандома)
    private fun handleOnSuccessLoadRandomTopRatedMovie(randomTopRatedMovie: Movie) {
        randomTopRatedMovieData.value = randomTopRatedMovie.toMovieView()
    }

    fun loadMovieOfTheWeek(page: Int) =
        useCaseFirstNowPlayingMovie.execute(params = None())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadMovieOfTheWeek, ::handleFailure)

    private fun handleOnSuccessLoadMovieOfTheWeek(movie: Movie) {
        movieOfTheWeek.value = movie.toNowPlayingMovieView()
    }

    //todo изменить на правильное отображение для двух нижних карточек
    fun loadTvShowPremier(page: Int) =
        useCaseNowPlayingMovies.execute(params = GetNowPlayingMovies.Params(page = page))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadTvShowPremier, ::handleFailure)

    private fun handleOnSuccessLoadTvShowPremier(list: List<Movie>) {
        tvShowPremier.value = list[0].toNowPlayingMovieView()
    }
}