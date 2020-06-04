package com.geekbrains.team.filmlibrary.adapters

interface OnItemSelectedListener {
    fun openMovieDetails(id: Int)
    fun showProgress()
    fun hideProgress()
    fun openSeriesDetails(id: Int)
}