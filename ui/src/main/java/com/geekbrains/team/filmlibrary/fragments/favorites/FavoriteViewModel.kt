package com.geekbrains.team.filmlibrary.fragments.favorites

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.movies.favoriteMovies.interactor.AddFavoriteMovieIdUseCase
import com.geekbrains.team.domain.movies.favoriteMovies.interactor.GetFavoriteMoviesUseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.toMovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val addFavoriteMovieIdUseCase: AddFavoriteMovieIdUseCase
) : BaseViewModel() {
    val favoriteMoviesLiveData = MutableLiveData<List<MovieView>>()

    fun loadFavoriteMovies() {
        val disposable = getFavoriteMoviesUseCase.execute(None())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadFavoriteMovies, ::handleFailure)

        addDisposable(disposable)
    }

    private fun handleOnSuccessLoadFavoriteMovies(data: List<Movie>) {

        favoriteMoviesLiveData.value = data.map { it.toMovieView() }
    }
}