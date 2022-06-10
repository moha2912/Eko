package com.example.eko.data.local

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.eko.data.MapLocationDao
import com.example.eko.data.model.db.MapLocationEntity

/**
 * Created by Moha on 2022-06-08.
 */

@Database(
    entities = [MapLocationEntity::class],
    version = DatabaseMigrations.DB_VERSION,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun mapLocationDao(): MapLocationDao


    companion object {
        const val DB_NAME = "Eko_db"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}