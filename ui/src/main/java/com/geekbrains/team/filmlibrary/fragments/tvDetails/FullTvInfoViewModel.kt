package com.geekbrains.team.filmlibrary.fragments.tvDetails

import com.geekbrains.team.domain.tv.details.interactor.GetTVDetailsUseCase
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.model.PersonView
import com.geekbrains.team.filmlibrary.model.TVShowView
import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.similarMovie.interactor.GetSimilarMoviesUseCase
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.similarTvShows.interactor.GetSimilarTVShowsUseCase
import com.geekbrains.team.filmlibrary.model.toPersonView
import com.geekbrains.team.filmlibrary.model.toTVShowView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FullTvInfoViewModel @Inject constructor(
    private val getTVDetailsUseCase: GetTVDetailsUseCase,
    private val getSimilarTVShowsUseCase: GetSimilarTVShowsUseCase
): BaseViewModel() {
    val tvDetailsLiveData: MutableLiveData<TVShowView> = MutableLiveData()
    val actorsLiveData: MutableLiveData<List<PersonView>> = MutableLiveData()
    val crewLiveData: MutableLiveData<List<PersonView>> = MutableLiveData()
    val similarTVShowsLiveData: MutableLiveData<List<TVShowView>> = MutableLiveData()

    fun loadTVShowInfo(id: Int) {
        val disposable = getTVDetailsUseCase.execute(params = GetTVDetailsUseCase.Params(id = id))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadMovieDetails, ::handleFailure)

        addDisposable(disposable)
    }

    fun loadSimilarMovies(id: Int, page: Int) {
        val disposable = getSimilarTVShowsUseCase.execute(GetSimilarTVShowsUseCase.Params(id, page))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadSimilarMovies, ::handleFailure)
        addDisposable(disposable)
    }

    private fun handleOnSuccessLoadMovieDetails(details: TVShow) {
        tvDetailsLiveData.value = details.toTVShowView()
        actorsLiveData.value = details.cast?.map { it.toPersonView() }
        crewLiveData.value = details.crew?.map { it.toPersonView() }
    }

    private fun handleOnSuccessLoadSimilarMovies(movies: List<TVShow>) {
        similarTVShowsLiveData.value = movies.map { it.toTVShowView() }
    }
}