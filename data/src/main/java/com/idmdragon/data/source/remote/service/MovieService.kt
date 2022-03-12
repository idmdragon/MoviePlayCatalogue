package com.idmdragon.data.source.remote.service

import com.idmdragon.data.source.remote.response.GeneralResponse
import com.idmdragon.data.source.remote.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/movie/now_playing")
    suspend fun getMovieNowPlaying(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieResponse>

    @GET("/3/movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieResponse>

    @GET("/3/movie/top_rated")
    suspend fun getMovieTopRated(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieResponse>

    @GET("/3/movie/popular")
    suspend fun getMovieUpcoming(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieResponse>

}