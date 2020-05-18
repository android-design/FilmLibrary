package com.geekbrains.team.domain.actors.details.interactor

import com.geekbrains.team.domain.actors.details.repository.ActorDetailsRepository
import com.geekbrains.team.domain.actors.model.ActorInformation
import com.geekbrains.team.domain.base.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetActorDetailsUseCase @Inject constructor(
    private val actorDetailsRepository: ActorDetailsRepository,
    private val

):
    UseCase<ActorInformation, GetActorDetailsUseCase.Params> {

    override fun execute(params: Params): Single<out ActorInformation> {
        TODO("Not yet implemented")
    }

    data class Params(
        val id: Int
    )

}