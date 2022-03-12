package com.idmdragon.data.source.local

import com.idmdragon.data.source.local.dao.MovieTvDao
import com.idmdragon.data.source.local.entities.MovieTvEntities
import com.idmdragon.data.utils.MovieType
import kotlinx.coroutines.flow.Flow

class MovieLocal(private val movieTvDao: MovieTvDao) {

    suspend fun insertMovie(entity: MovieTvEntities) =
        movieTvDao.insertMovie(entity)

    fun getMoviePopular(): Flow<List<MovieTvEntities>> =
        movieTvDao.getMoviesByType(MovieType.POPULAR.name)

    fun getMovieNowPlaying(): Flow<List<MovieTvEntities>> =
        movieTvDao.getMoviesByType(MovieType.NOW_PLAYING.name)

    fun getMovieTopRated(): Flow<List<MovieTvEntities>> =
        movieTvDao.getMoviesByType(MovieType.TOP_RATED.name)

    fun getMovieUpcoming(): Flow<List<MovieTvEntities>> =
        movieTvDao.getMoviesByType(MovieType.UPCOMING.name)

    fun getMovieDetail(movieId: Int): Flow<MovieTvEntities> =
        movieTvDao.getMovieDetail(movieId)
}