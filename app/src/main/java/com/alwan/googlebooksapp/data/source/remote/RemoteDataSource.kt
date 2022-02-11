package com.alwan.googlebooksapp.data.source.remote

import android.util.Log
import com.alwan.googlebooksapp.data.source.remote.response.BookResponse
import com.alwan.googlebooksapp.data.source.remote.response.VolumeResponse
import com.alwan.googlebooksapp.network.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getBooks(callback: LoadBooksCallback, query: String) {
        val client = RetrofitConfig.apiInstance.getBooks(query)
        client.enqueue(object : Callback<VolumeResponse> {
            override fun onResponse(
                call: Call<VolumeResponse>,
                response: Response<VolumeResponse>
            ) {
                val items = response.body()?.items
                val listBook = ArrayList<BookResponse>()
                if (items != null) {
                    for(book in items){
                        listBook.add(book.volumeInfo)
                    }
                    callback.onBooksLoaded(listBook)
                } else {
                    callback.onBooksLoaded(null)
                }
            }
            override fun onFailure(call: Call<VolumeResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getBooks onFailure : ${t.message}")
            }
        })
    }

    interface LoadBooksCallback {
        fun onBooksLoaded(books: ArrayList<BookResponse>?)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}