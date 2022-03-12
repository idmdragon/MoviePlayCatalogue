package com.idmdragon.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idmdragon.data.source.local.dao.MovieDao
import com.idmdragon.data.source.local.entities.MovieEntities


@Database(
    entities = [ MovieEntities::class ],
    version = 1,
    exportSchema = false
)
abstract class MoviePlayDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao


}