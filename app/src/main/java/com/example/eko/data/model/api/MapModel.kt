package com.example.eko.data.model.api

import com.example.eko.data.model.db.MapLocationEntity

class MapModel(
    val id: Long? = 0,
    val LatLocation: Double? = null,
    val LongLocation: Double? = null,
    val Name: String? = "",
    val DateTime: Long? = 0,
) {
    fun mapToMapEntity() = MapLocationEntity(
        serverId = id,
        latLoc = LatLocation ?: 0.toDouble(),
        longLoc = LongLocation ?: 0.toDouble(),
        name = Name,
        date = DateTime
    )

    fun mapToMapEntityWithLocalId(localId: Long) = MapLocationEntity(
        id = localId,
        serverId = id,
        latLoc = LatLocation ?: 0.toDouble(),
        longLoc = LongLocation ?: 0.toDouble(),
        name = Name,
        date = DateTime
    )
}
