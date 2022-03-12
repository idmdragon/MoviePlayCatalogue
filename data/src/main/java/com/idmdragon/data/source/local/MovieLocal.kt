package com.idmdragon.data.source.local

import com.idmdragon.data.source.local.dao.MovieDao
import com.idmdragon.data.source.local.entities.MovieEntities
import com.idmdragon.data.utils.MovieType
import kotlinx.coroutines.flow.Flow

class MovieLocal(private val movieDao: MovieDao) {

    suspend fun insertMovie(entity: MovieEntities) =
        movieDao.insertMovie(entity)

    fun getMoviePopular(): Flow<List<MovieEntities>> =
        movieDao.getMoviesByType(MovieType.POPULAR.name)

    fun getMovieNowPlaying(): Flow<List<MovieEntities>> =
        movieDao.getMoviesByType(MovieType.NOW_PLAYING.name)

    fun getMovieTopRated(): Flow<List<MovieEntities>> =
        movieDao.getMoviesByType(MovieType.TOP_RATED.name)

    fun getMovieUpcoming(): Flow<List<MovieEntities>> =
        movieDao.getMoviesByType(MovieType.UPCOMING.name)

    fun getMovieDetail(movieId: Int): Flow<MovieEntities> =
        movieDao.getMovieDetail(movieId)
}