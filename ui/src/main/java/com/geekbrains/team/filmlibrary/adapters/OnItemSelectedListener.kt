package com.geekbrains.team.filmlibrary.adapters

interface OnItemSelectedListener {
    fun mainOpenItemInfo(id: Int)
    fun topOpenItemInfo(id: Int)
    fun searchOpenItemInfo(id: Int)
    fun favoriteOpenItemInfo(id: Int)
}