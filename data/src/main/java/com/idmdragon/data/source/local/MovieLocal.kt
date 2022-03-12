package com.idmdragon.data.source.local

import com.idmdragon.data.source.local.dao.MovieDao
import com.idmdragon.data.source.local.entities.MovieEntities
import com.idmdragon.data.utils.MovieType
import kotlinx.coroutines.flow.Flow

class MovieLocal (private val movieDao: MovieDao) {

    suspend fun insertListMovie(entities: List<MovieEntities>) =
        movieDao.insertListMovie(entities)

    suspend fun insertMovie(entity: MovieEntities) =
        movieDao.insertMovie(entity)

    fun getPopularMovie() : Flow<List<MovieEntities>> =
        movieDao.getNowPlaying(MovieType.NOW_PLAYING.name)

}