package com.geekbrains.team.filmlibrary.fragments.favoriteMovies

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.movies.favoriteMovie.interactor.GetFavoriteMoviesUseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoriteMoviesViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : BaseViewModel() {
    val favoriteMoviesData = MutableLiveData<List<Movie>>()

    fun loadFavoriteMovies() {
        getFavoriteMoviesUseCase.execute(None())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadFavoriteMovies, ::handleFailure)
    }

    private fun handleOnSuccessLoadFavoriteMovies(data: List<Movie>) {
        favoriteMoviesData.value = data
    }
}