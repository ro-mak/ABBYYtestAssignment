package ru.makproductions.abbyytestassignment.presenter.main

import android.os.AsyncTask
import ru.makproductions.abbyytestassignment.model.entity.Cats
import ru.makproductions.abbyytestassignment.model.repo.CatsRepo
import javax.inject.Inject

class MainPresenterImpl : MainPresenter {
    @Inject
    lateinit var catsRepo: CatsRepo

    override fun loadAllCats() {
        catsRepo.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Cats.values())
    }

    override fun onCreate() {
        loadAllCats()
    }
}