package com.geekbrains.team.filmlibrary.adapters

interface OnItemSelectedListener {
    fun openMovieDetails(id: Int){

    }
    fun addId(id: Int){}
    fun removeId(id: Int){}
}