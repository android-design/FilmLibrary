package com.geekbrains.team.filmlibrary.fragments.favoriteMovies

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.movies.favoriteMovie.interactor.GetFavoriteMoviesIdsUseCase
import com.geekbrains.team.domain.movies.favoriteMovie.interactor.GetFavoriteMoviesUseCase
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.movieDetails.interactor.GetMoviesDetailsUseCase
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import io.reactivex.FlowableSubscriber
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers.io
import org.reactivestreams.Subscription
import javax.inject.Inject

class FavoriteMoviesViewModel @Inject constructor(
    private val getFavoriteMoviesIdsUseCase: GetFavoriteMoviesIdsUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase): BaseViewModel() {
    val liveData = MutableLiveData<List<Movie>>()

    fun loadFavoriteMoviesIds() {
        getFavoriteMoviesUseCase.execute(None()).subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Movie>>(){
                override fun onSuccess(t: List<Movie>) {
                    liveData.value = t
                }

                override fun onError(e: Throwable) {

                }
            })
    }
}