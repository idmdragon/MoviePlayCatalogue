package com.idmdragon.data.source.remote.service

import com.idmdragon.data.source.remote.response.GeneralResponse
import com.idmdragon.data.source.remote.response.MovieTvResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("/3/movie/now_playing")
    suspend fun getMovieNowPlaying(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieTvResponse>

    @GET("/3/movie/popular")
    suspend fun getMoviePopular(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieTvResponse>

    @GET("/3/movie/top_rated")
    suspend fun getMovieTopRated(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieTvResponse>

    @GET("/3/movie/upcoming")
    suspend fun getMovieUpcoming(
        @Query("api_key")
        api_key: String
    ): GeneralResponse<MovieTvResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id")
        movieId: Int,
        @Query("api_key")
        api_key: String
    ): MovieTvResponse
}