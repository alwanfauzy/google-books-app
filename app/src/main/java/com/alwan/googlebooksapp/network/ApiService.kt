package com.alwan.googlebooksapp.network

import com.alwan.googlebooksapp.data.source.remote.response.VolumeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("volumes")
    fun getBooks(
        @Query("q") query: String
    ): Call<VolumeResponse>
}