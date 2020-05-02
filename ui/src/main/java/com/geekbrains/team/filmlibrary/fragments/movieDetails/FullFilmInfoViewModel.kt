package com.geekbrains.team.filmlibrary.fragments.movieDetails

import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.movies.model.Movie
import com.geekbrains.team.domain.movies.movieDetails.interactor.GetMovieDetailsUseCase
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import com.geekbrains.team.filmlibrary.model.MovieView
import com.geekbrains.team.filmlibrary.model.toMovieView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FullFilmInfoViewModel @Inject constructor(
    private val useCaseMovieInfo: GetMovieDetailsUseCase
) : BaseViewModel() {

    val movieDetailsLiveData: MutableLiveData<MovieView> = MutableLiveData()

    fun loadMovieInfo(id: Int) {
        val disposable = useCaseMovieInfo.execute(params = GetMovieDetailsUseCase.Params(id = id))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadMovieDetails, ::handleFailure)

        addDisposable(disposable)
    }

    private fun handleOnSuccessLoadMovieDetails(details: Movie) {
        movieDetailsLiveData.value = details.toMovieView()
    }
}