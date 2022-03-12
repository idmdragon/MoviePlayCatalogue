package com.idmdragon.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idmdragon.data.source.local.entities.MovieTvEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieTvDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(entities: MovieTvEntities)

    @Query("SELECT * FROM MovieTvEntities WHERE movie_type = :type")
    fun getMoviesByType(type: String): Flow<List<MovieTvEntities>>

    @Query("SELECT * FROM MovieTvEntities WHERE id = :movieId")
    fun getMovieDetail(movieId: Int): Flow<MovieTvEntities>

}