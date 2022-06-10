package com.example.eko.data.api

import com.example.eko.data.model.api.MapDataModel
import com.example.eko.data.model.api.MapModelRes
import retrofit2.http.GET

/**
 * Created by Moha on 2022-06-08.
 */

interface ApiServices {

    @GET("v1/ec75f4d7-138d-4f3d-9f28-793408798957")
    suspend fun getData(): MapModelRes
}