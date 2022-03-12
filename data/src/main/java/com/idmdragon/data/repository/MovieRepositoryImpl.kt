package com.idmdragon.data.repository

import com.idmdragon.data.mapper.toEntities
import com.idmdragon.data.mapper.toFlowModels
import com.idmdragon.data.source.NetworkBoundResource
import com.idmdragon.data.source.local.MovieLocal
import com.idmdragon.data.source.local.entities.MovieEntities
import com.idmdragon.data.source.remote.MovieRemote
import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.data.source.remote.response.MovieResponse
import com.idmdragon.data.utils.MovieType
import com.idmdragon.domain.model.Movie
import com.idmdragon.domain.repository.MovieRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val remote: MovieRemote,
    private val local: MovieLocal
) : MovieRepository {

    override fun getMovieNowPlaying(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                local.getMovieNowPlaying().toFlowModels()

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remote.getMovieNowPlaying()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                data.map {
                    local.insertMovie(it.toEntities(MovieType.NOW_PLAYING.name))
                }
            }
        }.asFlow()

    override fun getMoviePopular(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                local.getMoviePopular().toFlowModels()

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remote.getMoviePopular()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                data.map {
                    local.insertMovie(it.toEntities(MovieType.POPULAR.name))
                }
            }
        }.asFlow()

    override fun getMovieTopRated(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }

    override fun getMovieUpcoming(): Flow<Resource<List<Movie>>> {
        TODO("Not yet implemented")
    }
}