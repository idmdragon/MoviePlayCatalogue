package com.idmdragon.domain.usecase

import com.idmdragon.domain.model.Movie
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMovieNowPlaying(): Flow<Resource<List<Movie>>>
    fun getMoviePopular(): Flow<Resource<List<Movie>>>
    fun getMovieTopRated(): Flow<Resource<List<Movie>>>
    fun getMovieUpcoming(): Flow<Resource<List<Movie>>>

}