package com.idmdragon.domain.repository

import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieNowPlaying(): Flow<Resource<List<MovieTv>>>
    fun getMoviePopular(): Flow<Resource<List<MovieTv>>>
    fun getMovieTopRated(): Flow<Resource<List<MovieTv>>>
    fun getMovieUpcoming(): Flow<Resource<List<MovieTv>>>
    fun getMovieDetail(movieId: Int, movieType: String): Flow<Resource<MovieTv>>
}