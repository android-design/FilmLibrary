package com.geekbrains.team.filmlibrary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.team.domain.movies.favoriteMovies.interactor.AddFavoriteMovieIdUseCase
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val addFavoriteMovieIdUseCase: AddFavoriteMovieIdUseCase
): BaseViewModel() {

    val liveData = MutableLiveData<String>()

    fun addMovieToFavorite(id: Int) {
        addFavoriteMovieIdUseCase.execute(AddFavoriteMovieIdUseCase.Params(id)).subscribeOn(
            Schedulers.io()
        )
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : DisposableCompletableObserver(){
                override fun onComplete() {
                    liveData.value = "Success"
                }

                override fun onError(e: Throwable) {
                    liveData.value = e.message ?: "Error"
                }
            })
    }
}