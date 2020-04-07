package com.geekbrains.team.filmlibrary.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private var failure: MutableLiveData<Throwable> = MutableLiveData()

    protected fun handleFailure(failure: Throwable) {
        this.failure.value = failure
    }
}