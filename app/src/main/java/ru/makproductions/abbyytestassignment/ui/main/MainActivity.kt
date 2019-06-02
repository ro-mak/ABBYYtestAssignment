package ru.makproductions.abbyytestassignment.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import ru.makproductions.abbyytestassignment.App
import ru.makproductions.abbyytestassignment.R
import ru.makproductions.abbyytestassignment.model.entity.Cat
import ru.makproductions.abbyytestassignment.presenter.main.MainPresenterImpl
import ru.makproductions.abbyytestassignment.ui.adapter.CatRecyclerAdapter
import ru.makproductions.abbyytestassignment.view.main.MainView

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenterImpl
    private lateinit var adapter: CatRecyclerAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val spanCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createPresenter()
        presenter.onCreate()
        val orientation = resources.configuration.orientation
        when (orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                layoutManager = LinearLayoutManager(this)
            }
            Configuration.ORIENTATION_LANDSCAPE -> {
                layoutManager = GridLayoutManager(this, spanCount)
            }
        }

        adapter = CatRecyclerAdapter()
        cats_recycler_view.layoutManager = layoutManager
        cats_recycler_view.adapter = adapter
    }

    override fun showCats(cats: List<Cat>) {
        adapter.cats = cats
    }

    fun createPresenter() {
        presenter = MainPresenterImpl(this)
        App.instance.appComponent.inject(presenter)
    }
}
