package com.geekbrains.team.domain.base

data class ResultWrapper<T>(val result: List<T>, val currentPage: Int)