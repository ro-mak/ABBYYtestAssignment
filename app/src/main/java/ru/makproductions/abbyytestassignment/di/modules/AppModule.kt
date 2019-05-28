package ru.makproductions.abbyytestassignment.di.modules

import dagger.Module
import dagger.Provides
import ru.makproductions.abbyytestassignment.App

@Module
class AppModule(private val app: App) {
    @Provides
    fun getApp() = app
}