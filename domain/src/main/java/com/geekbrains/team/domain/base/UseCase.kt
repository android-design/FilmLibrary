package com.geekbrains.team.domain.base

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UseCase<out R, in Params> {
    fun execute(params: Params): Single<out R>
}

interface UseCaseFlowable<R, in Params> {
    fun execute(params: Params): Flowable<R>
}

interface UseCaseWithoutParameters<R> {
    fun execute(): Single<R>
}

interface UseCaseCompletable<in Params> {
    fun execute(params: Params): Completable
}

class None