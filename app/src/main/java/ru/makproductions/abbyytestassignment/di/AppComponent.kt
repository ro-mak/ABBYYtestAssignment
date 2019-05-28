package ru.makproductions.abbyytestassignment.di

import dagger.Component
import ru.makproductions.abbyytestassignment.di.modules.AppModule
import ru.makproductions.abbyytestassignment.di.modules.CacheModule
import ru.makproductions.abbyytestassignment.di.modules.CatsRepoModule
import ru.makproductions.abbyytestassignment.presenter.main.MainPresenterImpl

@Component(modules = [CatsRepoModule::class, AppModule::class, CacheModule::class])
interface AppComponent {
    fun inject(mainPresenter: MainPresenterImpl)
}