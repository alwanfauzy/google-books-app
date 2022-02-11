package com.alwan.googlebooksapp.data.source.remote.response

data class VolumeResponse(
    val kind: String?,
    val totalItems: Int?,
    val items: ArrayList<ItemResponse>?
)
