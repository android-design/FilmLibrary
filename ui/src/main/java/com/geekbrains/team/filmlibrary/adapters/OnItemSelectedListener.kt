package com.geekbrains.team.filmlibrary.adapters

interface OnItemSelectedListener {
    fun openMovieDetails(id: Int)
    fun onLikeClick(id: Int)
}