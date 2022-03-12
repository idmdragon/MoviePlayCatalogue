package com.idmdragon.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.idmdragon.data.source.local.entities.MovieEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(entities: MovieEntities)

    @Query("SELECT * FROM MovieEntities WHERE movie_type = :type")
    fun getMoviesByType(type: String): Flow<List<MovieEntities>>

    @Query("SELECT * FROM MovieEntities WHERE id = :movieId")
    fun getMovieDetail(movieId: Int): Flow<MovieEntities>

}