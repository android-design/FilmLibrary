package com.geekbrains.team.filmlibrary.fragments.mainScreen

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.nowPlayingMovies.interactor.GetNowPlayingMovies
import com.geekbrains.team.domain.movies.topRatedMovies.interactor.GetTopRatedMovies
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
    private val useCaseTopRatedMovies: GetTopRatedMovies
) :
    BaseViewModel() {
    var nowPlayingMoviesData: MutableLiveData<List<NowPlayingMovieView>> = MutableLiveData()
    var upcomingMoviesData: MutableLiveData<List<UpcomingMovieView>> = MutableLiveData()
    var topRatedMoviesData: MutableLiveData<MovieView> = MutableLiveData()

    //todo изменить на правильное отображение для двух нижних карточек
    var movieOfTheWeek: MutableLiveData<NowPlayingMovieView> = MutableLiveData()
    var tvShowPremier: MutableLiveData<NowPlayingMovieView> = MutableLiveData()

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

    //берём рандомную страницу из первых 20
    fun loadTopRatedMovies() =
        useCaseTopRatedMovies.execute(params = GetTopRatedMovies.Params(page = generateRandomPage()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadTopRatedMovies, ::handleFailure)

    //берём рандомнуый фильм из страницы (для большего рандома)
    private fun handleOnSuccessLoadTopRatedMovies(list: List<Movie>) {
        topRatedMoviesData.value = list[generateRandomPage()].toMovieView()
    }

    private fun generateRandomPage(): Int {
        return (0..19).random()
    }


    //todo изменить на правильное отображение для двух нижних карточек
    fun loadMovieOfTheWeek(page: Int) =
        useCaseNowPlayingMovies.execute(params = GetNowPlayingMovies.Params(page = page))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadMovieOfTheWeek, ::handleFailure)

    private fun handleOnSuccessLoadMovieOfTheWeek(list: List<Movie>) {
        movieOfTheWeek.value = list[0].toNowPlayingMovieView()
    }

    fun loadTvShowPremier(page: Int) =
        useCaseNowPlayingMovies.execute(params = GetNowPlayingMovies.Params(page = page))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadTvShowPremier, ::handleFailure)

    private fun handleOnSuccessLoadTvShowPremier(list: List<Movie>) {
        tvShowPremier.value = list[0].toNowPlayingMovieView()
    }
}