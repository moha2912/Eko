package com.example.eko.data.api

import com.example.eko.data.model.api.MapDataModel
import com.example.eko.data.model.api.MapModelRes
import retrofit2.http.GET

/**
 * Created by Moha on 2022-06-08.
 */

interface ApiServices {

    @GET("v1/38fe91c8-a701-4608-8c3a-1eef4ab5fa32")
    suspend fun getData(): MapModelRes
}