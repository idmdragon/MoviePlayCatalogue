package com.idmdragon.data.source.local

import com.idmdragon.data.source.local.dao.MovieTvDao
import com.idmdragon.data.source.local.entities.MovieTvEntities
import com.idmdragon.data.utils.MovieType
import com.idmdragon.data.utils.TvType
import kotlinx.coroutines.flow.Flow

class TvLocal(private val movieTvDao: MovieTvDao) {

    suspend fun insertTv(entity: MovieTvEntities) =
        movieTvDao.insertMovieTv(entity)

    fun getTvAiringToday(): Flow<List<MovieTvEntities>> =
        movieTvDao.getMoviesTvByType(TvType.TV_AIRING_TODAY.name)

    fun getTvOnTheAir(): Flow<List<MovieTvEntities>> =
        movieTvDao.getMoviesTvByType(TvType.TV_ON_THE_AIR.name)

    fun getTvTopRated(): Flow<List<MovieTvEntities>> =
        movieTvDao.getMoviesTvByType(TvType.TV_TOP_RATED.name)

    fun getTvPopular(): Flow<List<MovieTvEntities>> =
        movieTvDao.getMoviesTvByType(TvType.TV_POPULAR.name)


}