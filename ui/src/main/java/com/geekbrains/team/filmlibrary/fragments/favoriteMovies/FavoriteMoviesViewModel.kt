package com.geekbrains.team.filmlibrary.fragments.favoriteMovies

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.favoriteMovie.interactor.GetFavoriteMoviesIdsUseCase
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
    private val getMoviesDetailsUseCase: GetMoviesDetailsUseCase): BaseViewModel() {
    val liveData = MutableLiveData<List<Movie>>()

    fun loadMovies(ids: List<Int>) {
        getMoviesDetailsUseCase.execute(ids).subscribeOn(io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(object : FlowableSubscriber<List<Movie>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(s: Subscription) {

                    }

                    override fun onNext(t: List<Movie>?) {
                        liveData.value = t
                    }

                    override fun onError(t: Throwable?) {
                        TODO("Not yet implemented")
                    }
                })
    }

    fun loadFavoriteMoviesIds() {
        getFavoriteMoviesIdsUseCase.execute().subscribeOn(io()).
        observeOn(AndroidSchedulers.mainThread()).
        subscribe()
        getFavoriteMoviesIdsUseCase.execute().subscribeOn(io()).
        observeOn(AndroidSchedulers.mainThread()).
        subscribe(object : DisposableSingleObserver<List<Int>>(){
            override fun onSuccess(result: List<Int>) {
                loadMovies(result)
            }

            override fun onError(e: Throwable) {

            }
        } )
    }
}