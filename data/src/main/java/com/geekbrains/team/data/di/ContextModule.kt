package com.geekbrains.team.data.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(context: Context) {
    var context: Context

    @Provides
    fun context(): Context {
        return context.getApplicationContext()
    }

    init {
        this.context = context
    }
}