package ru.makproductions.abbyytestassignment

import android.app.Application
import ru.makproductions.abbyytestassignment.di.AppComponent
import ru.makproductions.abbyytestassignment.di.DaggerAppComponent
import ru.makproductions.abbyytestassignment.di.modules.AppModule
import ru.makproductions.abbyytestassignment.model.room.db.CatsDatabase

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        CatsDatabase.create(this)
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    lateinit var appComponent: AppComponent
        private set
}