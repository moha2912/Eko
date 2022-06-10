package com.example.eko.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eko.data.model.api.MapDataModel
import com.example.eko.data.model.db.MapLocationEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Created by Moha on 2022-06-08.
 */

@Dao
interface MapLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMapData(mapLocationEntity: MapLocationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMapData(mapLocationEntity: List<MapLocationEntity>)

    @Query("SELECT * FROM map_location WHERE serverId =:serverId ")
    suspend fun getItemWithServerId(serverId: Long): MapLocationEntity?

    @Query("SELECT * from map_location WHERE deleted = '0' ORDER BY serverId")
    fun getMapFlow(): Flow<List<MapLocationEntity>>

    @ExperimentalCoroutinesApi
    fun getShowDistinctUntilChanged() =
        getMapFlow().distinctUntilChanged()
}