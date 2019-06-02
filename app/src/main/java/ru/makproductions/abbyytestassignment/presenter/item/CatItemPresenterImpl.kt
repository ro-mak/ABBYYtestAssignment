package ru.makproductions.abbyytestassignment.presenter.item

import ru.makproductions.abbyytestassignment.model.cache.ICache
import ru.makproductions.abbyytestassignment.view.item.CatItemView
import javax.inject.Inject

class CatItemPresenterImpl(val viewState: CatItemView) : CatItemPresenter {

    @Inject
    lateinit var cache: ICache

    override fun onCreate(catId: Int) {
        with(cache.cats) {
            if (this.isEmpty()) else viewState.showCat(this[catId])
        }
    }
}