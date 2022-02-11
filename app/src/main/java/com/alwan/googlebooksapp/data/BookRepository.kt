package com.alwan.googlebooksapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alwan.googlebooksapp.data.source.local.entity.BookEntity
import com.alwan.googlebooksapp.data.source.remote.RemoteDataSource
import com.alwan.googlebooksapp.data.source.remote.response.BookResponse
import com.alwan.googlebooksapp.utils.toBetterString

class BookRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    BookDataSource {
    override fun getBooks(query: String): LiveData<ArrayList<BookEntity>> {
        val booksResult = MutableLiveData<ArrayList<BookEntity>>()

        remoteDataSource.getBooks(object : RemoteDataSource.LoadBooksCallback {
            override fun onBooksLoaded(books: ArrayList<BookResponse>?) {
                val bookList = ArrayList<BookEntity>()

                if (books != null) {
                    for (response in books) {
                        with(response) {
                            val book = BookEntity(
                                id = id,
                                title = title,
                                authors = authors?.toBetterString() ?: "",
                                categories = categories?.toBetterString() ?: "",
                                description = description,
                                thumbnailLinks = imageLinks?.thumbnail,
                                previewLink = previewLink
                            )
                            bookList.add(book)
                        }
                    }
                    booksResult.postValue(bookList)
                } else {
                    booksResult.postValue(null)
                }
            }
        }, query)

        return booksResult
    }

    companion object {
        @Volatile
        private var instance: BookRepository? = null
        fun getInstance(remoteData: RemoteDataSource): BookRepository =
            instance ?: synchronized(this) {
                instance ?: BookRepository(remoteData)
            }
    }
}