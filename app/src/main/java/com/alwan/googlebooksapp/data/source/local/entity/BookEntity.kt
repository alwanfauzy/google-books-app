package com.alwan.googlebooksapp.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookEntity(
    val id: String?,
    val title: String?,
    val authors: String?,
    val categories: String?,
    val description: String?,
    val thumbnailLinks: String?,
    val previewLink: String?,
) : Parcelable
