package ru.makproductions.abbyytestassignment.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.makproductions.abbyytestassignment.App
import ru.makproductions.abbyytestassignment.R
import ru.makproductions.abbyytestassignment.presenter.main.MainPresenterImpl
import ru.makproductions.abbyytestassignment.ui.item.CatView
import ru.makproductions.abbyytestassignment.view.main.MainView

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createPresenter()
        presenter.onCreate()
        val view: CatView = findViewById(R.id.cat_view)
        view.imageView.setImageDrawable(getDrawable(R.drawable.ic_launcher_background))
        view.textView.text = "Cat to be placed here"
    }

    fun createPresenter() {
        presenter = MainPresenterImpl()
        App.instance.appComponent.inject(presenter)
    }
}
