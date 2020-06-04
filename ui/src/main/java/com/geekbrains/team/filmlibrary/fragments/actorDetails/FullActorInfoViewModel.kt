package com.geekbrains.team.filmlibrary.fragments.actorDetails

import com.geekbrains.team.domain.actors.credits.interactor.GetActorMovieCreditsUseCase
import com.geekbrains.team.domain.actors.credits.interactor.GetActorTVCreditsUseCase
import com.geekbrains.team.domain.actors.details.interactor.GetActorDetailsUseCase
import com.geekbrains.team.filmlibrary.base.BaseViewModel
import androidx.lifecycle.MutableLiveData
import com.geekbrains.team.domain.actors.model.ActorCreditsInfo
import com.geekbrains.team.domain.actors.model.ActorInformation
import com.geekbrains.team.filmlibrary.model.CreditsView
import com.geekbrains.team.filmlibrary.model.toCreditsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io

import javax.inject.Inject

class FullActorInfoViewModel @Inject constructor(
    private val getActorDetailsUseCase: GetActorDetailsUseCase,
    private val getActorMovieCreditsUseCase: GetActorMovieCreditsUseCase,
    private val getActorTVCreditsUseCase: GetActorTVCreditsUseCase
): BaseViewModel() {
    val detailsLiveData = MutableLiveData<ActorInformation>()
    val movieCreditsLiveData = MutableLiveData<CreditsView>()
    val tvCreditsLiveData= MutableLiveData<CreditsView>()

    fun loadDetails(id: Int) {
        getActorDetailsUseCase.execute(params = GetActorDetailsUseCase.Params(id))
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadActorDetails, ::handleFailure)

        getActorMovieCreditsUseCase.execute(GetActorMovieCreditsUseCase.Params(id))
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadMovieCredits, ::handleFailure)

        getActorTVCreditsUseCase.execute(GetActorTVCreditsUseCase.Params(id))
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handleOnSuccessLoadTVCredits, ::handleFailure)
    }

    private fun handleOnSuccessLoadActorDetails(details: ActorInformation) {
        detailsLiveData.value = details
    }

    private fun handleOnSuccessLoadMovieCredits(credits: ActorCreditsInfo) {
        movieCreditsLiveData.value = credits.toCreditsView()
    }

    private fun handleOnSuccessLoadTVCredits(credits: ActorCreditsInfo) {
        tvCreditsLiveData.value = credits.toCreditsView()
    }
}