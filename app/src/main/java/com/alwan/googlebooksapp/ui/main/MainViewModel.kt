package com.alwan.googlebooksapp.ui.main

import androidx.lifecycle.ViewModel
import com.alwan.googlebooksapp.data.BookRepository

class MainViewModel(private val bookRepository: BookRepository) : ViewModel() {
    fun getBooks(query: String) = bookRepository.getBooks(query)
}