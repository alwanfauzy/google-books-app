package com.alwan.googlebooksapp.di

import com.alwan.googlebooksapp.data.BookRepository
import com.alwan.googlebooksapp.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): BookRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return BookRepository.getInstance(remoteDataSource)
    }
}