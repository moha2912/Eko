package com.example.eko.data.repository

import com.example.eko.data.model.api.MapModelRes
import com.example.eko.other.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Moha on 2022-06-08.
 */

interface MapRepository {

    suspend fun getAll(): Flow<Resource<Boolean>>
}