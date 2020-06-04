package com.geekbrains.team.filmlibrary.adapters

interface Binder {
    fun <T, S> bind(data: T, position: Int = 0, listener: S?)
}