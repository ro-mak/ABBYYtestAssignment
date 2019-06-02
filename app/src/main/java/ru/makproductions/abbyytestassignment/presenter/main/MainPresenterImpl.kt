package ru.makproductions.abbyytestassignment.presenter.main

import android.arch.lifecycle.Observer
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import ru.makproductions.abbyytestassignment.App
import ru.makproductions.abbyytestassignment.model.entity.Cat
import ru.makproductions.abbyytestassignment.model.entity.Cats
import ru.makproductions.abbyytestassignment.model.repo.CatsRepo
import ru.makproductions.abbyytestassignment.view.main.MainView
import javax.inject.Inject

class MainPresenterImpl(viewState: MainView) : MainPresenter {
    @Inject
    lateinit var catsRepo: CatsRepo

    val catsObserver = Observer<List<Cat>>({ cats ->
        cats?.let { viewState.showCats(it) } ?: let {
            Log.e(
                "MainPresenter",
                "cats are null"
            )
        }
    })


    override fun loadAllCats() {
        Log.e("loadAllCats ", " outside")
        val deffered = GlobalScope.async {
            Log.e("loadAllCats ", " inside")
            catsRepo.doInBackground(Cats.values())
            Toast.makeText(App.instance, "Cats loaded", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate() {
        catsRepo.getCatsLiveData().observeForever(catsObserver)
        loadAllCats()
    }

}