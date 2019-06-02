package ru.makproductions.abbyytestassignment.model.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.graphics.BitmapFactory
import android.util.Log
import ru.makproductions.abbyytestassignment.model.cache.ICache
import ru.makproductions.abbyytestassignment.model.entity.Cat
import ru.makproductions.abbyytestassignment.model.entity.Cats
import java.net.HttpURLConnection
import java.net.URL

class CatsRepo(val cache: ICache) {
    private val catsLiveData = MutableLiveData<List<Cat>>()
    fun getCatsLiveData(): LiveData<List<Cat>> {
        return catsLiveData
    }

    fun doInBackground(params: Array<Cats>?) {
        cache.subscribeToData(catsLiveData)
        var httpURLConnection: HttpURLConnection
        val resultList: MutableList<Cat> = mutableListOf()
        try {
            for (catId in 0 until params!!.size) {
                val cat: Cats = params[catId]
                val url = URL(cat.path)
                httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "GET"
                httpURLConnection.readTimeout = 10000
                httpURLConnection.connect()
                val data: ByteArray = httpURLConnection.inputStream.readBytes()
                val image = BitmapFactory.decodeByteArray(data, 0, data.size)
                val catWithImage = Cat(name = cat.catName, image = image, catId = catId)
                resultList.add(catWithImage)
            }
            onPostExecute(resultList)
        } catch (e: Exception) {
            Log.e("CatsRepo load error", e.message)
        }
    }

    fun onPostExecute(result: List<Cat>?) {
        Log.e("onPostExecute ", result?.toString())
        catsLiveData.postValue(result)
    }
}