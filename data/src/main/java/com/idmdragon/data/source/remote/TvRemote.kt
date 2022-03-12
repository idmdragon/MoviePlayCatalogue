package com.idmdragon.data.source.remote

import com.idmdragon.data.BuildConfig
import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.data.source.remote.response.MovieTvResponse
import com.idmdragon.data.source.remote.service.MovieService
import com.idmdragon.data.source.remote.service.TvService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvRemote(private val tvService: TvService) {

    fun getTvAiringToday(): Flow<ApiResponse<List<MovieTvResponse>>> =
        flow {
            try {
                val response = tvService.getTvAiringToday(BuildConfig.API_KEY)
                emit(ApiResponse.Success(response.results))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getTvOnTheAir(): Flow<ApiResponse<List<MovieTvResponse>>> =
        flow {
            try {
                val response = tvService.getTvOnTheAir(BuildConfig.API_KEY)
                emit(ApiResponse.Success(response.results))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getTvTopRated(): Flow<ApiResponse<List<MovieTvResponse>>> =
        flow {
            try {
                val response = tvService.getTvTopRated(BuildConfig.API_KEY)
                emit(ApiResponse.Success(response.results))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getTvPopular(): Flow<ApiResponse<List<MovieTvResponse>>> =
        flow {
            try {
                val response = tvService.getTvPopular(BuildConfig.API_KEY)
                emit(ApiResponse.Success(response.results))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getMovieById(tvId:Int): Flow<ApiResponse<MovieTvResponse>> =
        flow {
            try {
                val response = tvService.getDetailTv(tvId, BuildConfig.API_KEY)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

}