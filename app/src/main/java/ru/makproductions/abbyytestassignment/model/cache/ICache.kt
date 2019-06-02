package ru.makproductions.abbyytestassignment.model.cache

import android.arch.lifecycle.LiveData
import ru.makproductions.abbyytestassignment.model.entity.Cat

interface ICache {
    var cats: List<Cat>
    fun getCatsLiveData(): LiveData<List<Cat>>
    fun subscribeToData(catsLiveData: LiveData<List<Cat>>)
}