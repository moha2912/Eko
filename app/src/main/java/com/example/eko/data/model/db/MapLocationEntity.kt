package com.example.eko.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eko.utils.Exp

@Entity(tableName = "map_location")
data class MapLocationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long? = null,
    @ColumnInfo(name = "serverId") var serverId: Long? = null,
    @ColumnInfo(name = "longLoc") var longLoc: Double = 0 as Double,
    @ColumnInfo(name = "latLoc") var latLoc: Double = 0 as Double,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "date") var date: Long? = null,
)