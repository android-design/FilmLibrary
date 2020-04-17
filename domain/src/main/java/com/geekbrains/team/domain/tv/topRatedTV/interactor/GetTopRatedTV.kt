package com.geekbrains.team.domain.tv.topRatedTV.interactor

import com.geekbrains.team.domain.base.None
import com.geekbrains.team.domain.base.UseCase
import com.geekbrains.team.domain.tv.model.TVShow
import com.geekbrains.team.domain.tv.topRatedTV.repository.TopRatedTVRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetTopRatedTV @Inject constructor(private val repository: TopRatedTVRepository) :
    UseCase<List<TVShow>, None> {
    override fun execute(params: None): Single<MutableList<TVShow>> = Observable.range(1, 10)
        .concatMapSingle { page -> repository.fetch(page) }
        .takeWhile { it.isNotEmpty() }
        .scan { t1: MutableList<TVShow>, t2: MutableList<TVShow> ->
            t1.addAll(t2)
            t1
        }
        .lastOrError()
}