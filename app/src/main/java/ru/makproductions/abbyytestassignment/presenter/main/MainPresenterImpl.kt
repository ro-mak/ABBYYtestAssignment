package ru.makproductions.abbyytestassignment.presenter.main

import android.arch.lifecycle.Observer
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import ru.makproductions.abbyytestassignment.App
import ru.makproductions.abbyytestassignment.model.cache.ICache
import ru.makproductions.abbyytestassignment.model.entity.Cat
import ru.makproductions.abbyytestassignment.model.entity.Cats
import ru.makproductions.abbyytestassignment.model.repo.CatsRepo
import ru.makproductions.abbyytestassignment.view.main.MainView
import javax.inject.Inject

class MainPresenterImpl(val viewState: MainView) : MainPresenter {
    @Inject
    lateinit var catsRepo: CatsRepo
    @Inject
    lateinit var cache: ICache

    val catsObserver = Observer<List<Cat>> { cats ->
        Log.e("catsObserver", "catch")
        cats?.let { if (it.isEmpty()) loadAllCats() else viewState.showCats(it) } ?: let {
            loadAllCats()
            Log.e(
                "Cache",
                "cats are null"
            )
        }
    }

    override fun onSwipeRefresh() {
        loadAllCats()
    }

    override fun loadAllCats() {
        Log.e("loadAllCats ", "start loading")
        val deffered = GlobalScope.async {
            catsRepo.doInBackground(Cats.values())
            Toast.makeText(App.instance, "Cats loaded", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate() {
        cache.getCatsLiveData().observeForever(catsObserver)
    }

    override fun onFinish() {
        catsRepo.getCatsLiveData().removeObserver(catsObserver)
    }

}