package com.idmdragon.domain.repository

import com.idmdragon.domain.model.Movie
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieNowPlaying(): Flow<Resource<List<Movie>>>
    fun getMoviePopular(): Flow<Resource<List<Movie>>>
    fun getMovieTopRated(): Flow<Resource<List<Movie>>>
    fun getMovieUpcoming(): Flow<Resource<List<Movie>>>
    fun getMovieDetail(movieId: Int, movieType: String): Flow<Resource<Movie>>
}