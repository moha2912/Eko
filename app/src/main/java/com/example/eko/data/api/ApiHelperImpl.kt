package com.example.eko.data.api

import com.example.eko.data.model.api.MapDataModel
import com.example.eko.data.model.api.MapModelRes
import javax.inject.Inject

/**
 * Created by Moha on 2022-06-08.
 */

class ApiHelperImpl @Inject constructor(
    private val apiServices: ApiServices
) : ApiHelper {

    override suspend fun getData(): MapModelRes = apiServices.getData()

}