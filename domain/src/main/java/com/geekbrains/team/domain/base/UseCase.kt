package com.geekbrains.team.domain.base

import io.reactivex.Single

interface SingleUseCase<R, in Params> {
    fun execute(params: Params): Single<R>
}

class None