package com.idmdragon.data.source.remote

import com.idmdragon.data.BuildConfig
import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.data.source.remote.response.MovieResponse
import com.idmdragon.data.source.remote.service.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRemote(private val movieService: MovieService) {

    fun getMovieNowPlaying(): Flow<ApiResponse<List<MovieResponse>>> =
        flow {
            try {
                val response = movieService.getMovieNowPlaying(BuildConfig.API_KEY)
                emit(ApiResponse.Success(response.results))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getMoviePopular(): Flow<ApiResponse<List<MovieResponse>>> =
        flow {
            try {
                val response = movieService.getMoviePopular(BuildConfig.API_KEY)
                emit(ApiResponse.Success(response.results))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getMovieTopRated(): Flow<ApiResponse<List<MovieResponse>>> =
        flow {
            try {
                val response = movieService.getMovieTopRated(BuildConfig.API_KEY)
                emit(ApiResponse.Success(response.results))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getMovieUpcoming(): Flow<ApiResponse<List<MovieResponse>>> =
        flow {
            try {
                val response = movieService.getMovieUpcoming(BuildConfig.API_KEY)
                emit(ApiResponse.Success(response.results))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

}