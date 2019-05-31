package ru.makproductions.abbyytestassignment.presenter.main

import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import ru.makproductions.abbyytestassignment.App
import ru.makproductions.abbyytestassignment.model.entity.Cats
import ru.makproductions.abbyytestassignment.model.repo.CatsRepo
import javax.inject.Inject

class MainPresenterImpl : MainPresenter {
    @Inject
    lateinit var catsRepo: CatsRepo


    override fun loadAllCats() {
        Log.e("loadAllCats ", " outside")
        val deffered = GlobalScope.async {
            Log.e("loadAllCats ", " inside")
            catsRepo.doInBackground(Cats.values())
            Toast.makeText(App.instance, "Cats loaded", Toast.LENGTH_LONG).show()
        }


    }

    override fun onCreate() {
        loadAllCats()
    }
}