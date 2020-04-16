package com.geekbrains.team.filmlibrary.fragments.favoriteMovies

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.movies.favoriteMovie.interactor.GetFavoriteMoviesUseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

class FavoriteMoviesViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase): BaseViewModel() {
    val liveData = MutableLiveData<List<Movie>>()

    @SuppressLint("CheckResult")
    fun loadFavoriteMovies() {
        getFavoriteMoviesUseCase.execute(None()).subscribeOn(io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadFavoriteMovies, ::handleFailure)
    }

    private fun handleOnSuccessLoadFavoriteMovies(list: List<Movie>){
        liveData.value = list
    }

}