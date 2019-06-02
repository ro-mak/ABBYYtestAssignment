package ru.makproductions.abbyytestassignment.model.room.cache

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.makproductions.abbyytestassignment.App
import ru.makproductions.abbyytestassignment.model.cache.ICache
import ru.makproductions.abbyytestassignment.model.entity.Cat
import ru.makproductions.abbyytestassignment.model.room.db.CatsDatabase
import ru.makproductions.abbyytestassignment.model.room.entity.RoomCat
import java.io.ByteArrayOutputStream

class RoomCache : ICache {

    override var cats: List<Cat> = listOf()
    private val catsLiveData = MutableLiveData<List<Cat>>()

    val IO = Dispatchers.IO

    init {
        loadCats()
    }

    override fun getCatsLiveData(): LiveData<List<Cat>> {
        return catsLiveData
    }

    private fun loadCats() {
        Log.e("loadCats", "trying")
        GlobalScope.launch {
            Log.e("loadCats", "loading")
            try {
                val list = if (cats.isEmpty()) mutableListOf<Cat>() else cats as MutableList<Cat>
                val savedCatNames = CatsDatabase.getInstance()?.getCatsDao()?.findAllCats()
                savedCatNames?.let {
                    for ((index, roomCat) in savedCatNames.withIndex()) {
                        val bitmapData = App.instance.openFileInput(roomCat.name).readBytes()
                        val bitmap = BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.size)
                        list.add(Cat(roomCat.name, bitmap, index))
                    }
                    cats = list
                    Dispatchers.Main.dispatch(this.coroutineContext, Runnable {
                        Log.e("Dispatching", "loadCats from cache")
                        catsLiveData.value = cats
                    })
                    Log.e("loadCats", "load finished")
                } ?: let {
                    Dispatchers.Main.dispatch(this.coroutineContext, Runnable {
                        Log.e("Dispatching", "loadCats from internet")
                        catsLiveData.value = null
                    })
                }
            } catch (e: Exception) {
                Log.e("loadCats", e.message)
            }
        }
    }


    val catsCacheObserver = Observer<List<Cat>> { cats ->
        Log.e("CatsObserver", "catch")
        cats?.let {
            Log.e("CatsObserver", "saving")
            this.cats = it
            catsLiveData.value = it
            saveCats(it)
        } ?: let {
            Log.e(
                "Cache",
                "cats are null"
            )
        }
    }

    override fun subscribeToData(catsLiveData: LiveData<List<Cat>>) {
        catsLiveData.observeForever(catsCacheObserver)
    }

    private fun saveCats(catList: List<Cat>) {
        Log.e("saveCats", "starting")
        for (cat in catList) {
            GlobalScope.launch {
                Log.e("saveCats", "saving")
                val byteArrayOutputStream = ByteArrayOutputStream()
                val context = App.instance
                CatsDatabase.getInstance()?.getCatsDao()?.insert(RoomCat(cat.catId, cat.name))
                byteArrayOutputStream.reset()
                cat.image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                context.openFileOutput(cat.name, Context.MODE_PRIVATE)
                    .use { it.write(byteArrayOutputStream.toByteArray()) }
            }
        }
    }

}