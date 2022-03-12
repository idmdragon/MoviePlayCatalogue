package com.idmdragon.data.source.remote.service

import com.idmdragon.data.source.remote.response.GeneralResponse
import com.idmdragon.data.source.remote.response.MovieTvResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvService {

    @GET("/3/tv/airing_today")
    suspend fun getTvAiringToday(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieTvResponse>

    @GET("/3/tv/on_the_air")
    suspend fun getTvOnTheAir(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieTvResponse>

    @GET("/3/tv/popular")
    suspend fun getTvPopular(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieTvResponse>

    @GET("/3/tv/top_rated")
    suspend fun getTvTopRated(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieTvResponse>

    @GET("/3/tv/{tv_id}")
    suspend fun getDetailTv(
        @Path("tv_id")
        tvId: Int,
        @Query("api_key")
        api_key: String
    ): MovieTvResponse
}