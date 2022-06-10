package com.example.eko.data.api

import com.example.eko.data.model.api.MapDataModel
import com.example.eko.data.model.api.MapModelRes

/**
 * Created by Moha on 2022-06-08.
 */

interface ApiHelper {
    suspend fun getData(): MapModelRes
}