package ru.makproductions.abbyytestassignment.view.main

import ru.makproductions.abbyytestassignment.model.entity.Cat

interface MainView {
    fun showCats(cats: List<Cat>)
}