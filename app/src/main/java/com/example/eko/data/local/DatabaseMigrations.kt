package com.example.eko.data.local

import androidx.room.migration.Migration

object DatabaseMigrations {
    const val DB_VERSION = 1

    val MIGRATIONS: Array<Migration>
        get() = arrayOf<Migration>(
        )
}