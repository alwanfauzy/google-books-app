package com.alwan.googlebooksapp.data.source.remote.response

data class BookResponse(
    val id: String?,
    val title: String?,
    val authors: ArrayList<String>?,
    val categories: ArrayList<String>?,
    val description: String?,
    val imageLinks: ImageLink?,
    val previewLink: String?,
)
