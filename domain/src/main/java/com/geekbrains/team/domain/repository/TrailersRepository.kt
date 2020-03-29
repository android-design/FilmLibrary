package com.geekbrains.team.domain.repository

import io.reactivex.Single

interface TrailersRepository {
    fun getTrailerById(id: Int): Single<String>
}