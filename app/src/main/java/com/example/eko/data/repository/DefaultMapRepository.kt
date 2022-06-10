package com.example.eko.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.room.Transaction
import com.example.eko.data.MapLocationDao
import com.example.eko.data.api.ApiHelperImpl
import com.example.eko.data.model.api.MapModelRes
import com.example.eko.data.model.db.MapLocationEntity
import com.example.eko.other.Resource
import com.example.eko.other.ResponseHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Moha on 2022-06-08.
 */

open class DefaultMapRepository @Inject constructor(
    private val apiHelperImpl: ApiHelperImpl,
    private val mapLocationDao: MapLocationDao
) {

    private val responseHandler = ResponseHandler()

    @Transaction
    suspend fun getAllFromApi(): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.loading(null))

            val res = apiHelperImpl.getData()
            Log.d("getAll", "getAll: $res")


            res.data.maps?.forEach { map ->
                val mapEntity = mapLocationDao.getItemWithServerId(map.id ?: 0)

                if (mapEntity == null) {
                    mapLocationDao.insertMapData(map.mapToMapEntity())
                } else {
                    mapLocationDao.insertMapData(map.mapToMapEntityWithLocalId(mapEntity.id ?: 0))
                }

            }
            emit(responseHandler.handleSuccess(true))
        } catch (e: Exception) {
            Log.d("getAll", "Exception: $e")
            emit(responseHandler.handleException(e))
        }

    }

    fun getAllFromDb(): Flow<List<MapLocationEntity>> {
        return mapLocationDao.getShowDistinctUntilChanged()
    }
}
