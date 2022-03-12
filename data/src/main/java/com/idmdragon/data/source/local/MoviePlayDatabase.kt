package com.idmdragon.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idmdragon.data.source.local.dao.MovieTvDao
import com.idmdragon.data.source.local.entities.MovieTvEntities


@Database(
    entities = [ MovieTvEntities::class ],
    version = 1,
    exportSchema = false
)
abstract class MoviePlayDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieTvDao


}