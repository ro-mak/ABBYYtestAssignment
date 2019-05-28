package ru.makproductions.abbyytestassignment.model.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import ru.makproductions.abbyytestassignment.App
import ru.makproductions.abbyytestassignment.model.entity.Cat
import ru.makproductions.abbyytestassignment.model.entity.Cats
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class CatsRepo : AsyncTask<Array<Cats>, Int, List<Cat>>() {
    private val catsLiveData = MutableLiveData<List<Cat>>()
    fun getCatsLiveData(): LiveData<List<Cat>> {
        return catsLiveData
    }

    override fun doInBackground(vararg params: Array<Cats>?): List<Cat>? {
        var httpsURLConnection: HttpsURLConnection
        val resultList: MutableList<Cat> = mutableListOf()
        try {
            for (cat in listOf(params[0]) as List<Cats>) {
                val url = URL(cat.path)
                httpsURLConnection = url.openConnection() as HttpsURLConnection
                httpsURLConnection.requestMethod = "GET"
                httpsURLConnection.readTimeout = 10000
                httpsURLConnection.connect()
                val data: ByteArray = httpsURLConnection.inputStream.readBytes()
                val image = BitmapFactory.decodeByteArray(data, 0, data.size)
                val catWithImage = Cat(name = cat.catName, image = image)
                resultList.add(catWithImage)
            }
            return resultList
        } catch (e: Exception) {
            Log.e("CatsRepo load error", e.stackTrace.toString())
        }
        return null
    }

    override fun onPostExecute(result: List<Cat>?) {
        super.onPostExecute(result)
        catsLiveData.postValue(result)
        Toast.makeText(App.instance, "Cats loaded", Toast.LENGTH_SHORT).show()
    }
}