package ru.makproductions.abbyytestassignment.ui.item

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.cat_view.*
import ru.makproductions.abbyytestassignment.App
import ru.makproductions.abbyytestassignment.R
import ru.makproductions.abbyytestassignment.model.entity.Cat
import ru.makproductions.abbyytestassignment.presenter.item.CatItemPresenterImpl
import ru.makproductions.abbyytestassignment.view.item.CatItemView

class CatItemActivity : AppCompatActivity(), CatItemView {

    private lateinit var presenter: CatItemPresenterImpl

    companion object {
        private val CAT_EXTRA = "CAT_NAME"
        fun start(context: Context, cat: Cat? = null, options: Bundle) {
            context.startActivity(Intent(context, CatItemActivity::class.java).apply {
                cat?.let {
                    putExtra(
                        CAT_EXTRA,
                        cat.catId
                    )
                }
            }, options)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_item_activity)
        createPresenter()
        intent?.let {
            it.extras?.let { extras ->
                extras.getInt(CAT_EXTRA).let { presenter.onCreate(it) }
            }
        }
    }

    fun createPresenter() {
        presenter = CatItemPresenterImpl(this)
        App.instance.appComponent.inject(presenter)
    }

    override fun showCat(cat: Cat) {
        cat_image_view.setImageBitmap(cat.image)
        cat_text_view.text = cat.name
    }
}