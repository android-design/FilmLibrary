package com.geekbrains.team.filmlibrary.adapters

interface Binder {
    fun <T> bind(data: T, position: Int = 0, listener: OnItemSelectedListener?)
}