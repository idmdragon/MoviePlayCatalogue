package com.idmdragon.domain.repository

import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TvRepository {
    fun getTvAiringToday(): Flow<Resource<List<MovieTv>>>
    fun getTvOnTheAir(): Flow<Resource<List<MovieTv>>>
    fun getTvTopRated(): Flow<Resource<List<MovieTv>>>
    fun getTvPopular(): Flow<Resource<List<MovieTv>>>
    fun getDetailTv(tvId: Int, tvType: String): Flow<Resource<MovieTv>>
}