package com.alwan.googlebooksapp.data

import androidx.lifecycle.LiveData
import com.alwan.googlebooksapp.data.source.local.entity.BookEntity

interface BookDataSource {
    fun getBooks(query: String): LiveData<ArrayList<BookEntity>>
}