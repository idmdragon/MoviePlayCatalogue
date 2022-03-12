package com.idmdragon.data.repository

import com.idmdragon.data.mapper.toEntities
import com.idmdragon.data.mapper.toFlowModel
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

    override fun getMovieTopRated(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                local.getMovieTopRated().toFlowModels()

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remote.getMovieTopRated()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                data.map {
                    local.insertMovie(it.toEntities(MovieType.TOP_RATED.name))
                }
            }
        }.asFlow()

    override fun getMovieUpcoming(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                local.getMovieUpcoming().toFlowModels()

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remote.getMovieUpcoming()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                data.map {
                    local.insertMovie(it.toEntities(MovieType.UPCOMING.name))
                }
            }
        }.asFlow()

    override fun getMovieDetail(movieId: Int, movieType: String): Flow<Resource<Movie>> =
        object : NetworkBoundResource <Movie, MovieResponse>() {
            override fun loadFromDB(): Flow<Movie> =
                local.getMovieDetail(movieId).toFlowModel()

            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remote.getMovieById(movieId)

            override suspend fun saveCallResult(data: MovieResponse) {
                    local.insertMovie(data.toEntities(movieType))
            }
        }.asFlow()
}