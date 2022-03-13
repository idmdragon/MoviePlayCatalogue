package com.idmdragon.data.repository

import com.idmdragon.data.mapper.toEntities
import com.idmdragon.data.mapper.toFlowModel
import com.idmdragon.data.mapper.toFlowModels
import com.idmdragon.data.source.NetworkBoundResource
import com.idmdragon.data.source.local.MovieLocal
import com.idmdragon.data.source.remote.MovieRemote
import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.data.source.remote.response.MovieTvResponse
import com.idmdragon.data.utils.MovieType
import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.repository.MovieRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MovieRepositoryImpl(
    private val remote: MovieRemote,
    private val local: MovieLocal
) : MovieRepository {

    override fun getMovieNowPlaying(): Flow<Resource<List<MovieTv>>> =
        object : NetworkBoundResource<List<MovieTv>, List<MovieTvResponse>>() {
            override fun loadFromDB(): Flow<List<MovieTv>> =
                local.getMovieNowPlaying().toFlowModels()

            override fun shouldFetch(data: List<MovieTv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieTvResponse>>> =
                remote.getMovieNowPlaying()

            override suspend fun saveCallResult(data: List<MovieTvResponse>) {
                data.map {
                    local.insertMovie(it.toEntities(MovieType.NOW_PLAYING.name))
                }
            }
        }.asFlow()

    override fun getMoviePopular(): Flow<Resource<List<MovieTv>>> =
        object : NetworkBoundResource<List<MovieTv>, List<MovieTvResponse>>() {
            override fun loadFromDB(): Flow<List<MovieTv>> =
                local.getMoviePopular().toFlowModels()

            override fun shouldFetch(data: List<MovieTv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieTvResponse>>> =
                remote.getMoviePopular()

            override suspend fun saveCallResult(data: List<MovieTvResponse>) {
                data.map {
                    local.insertMovie(it.toEntities(MovieType.POPULAR.name))
                }
            }
        }.asFlow()

    override fun getMovieTopRated(): Flow<Resource<List<MovieTv>>> =
        object : NetworkBoundResource<List<MovieTv>, List<MovieTvResponse>>() {
            override fun loadFromDB(): Flow<List<MovieTv>> =
                local.getMovieTopRated().toFlowModels()

            override fun shouldFetch(data: List<MovieTv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieTvResponse>>> =
                remote.getMovieTopRated()

            override suspend fun saveCallResult(data: List<MovieTvResponse>) {
                data.map {
                    local.insertMovie(it.toEntities(MovieType.TOP_RATED.name))
                }
            }
        }.asFlow()

    override fun getMovieUpcoming(): Flow<Resource<List<MovieTv>>> =
        object : NetworkBoundResource<List<MovieTv>, List<MovieTvResponse>>() {
            override fun loadFromDB(): Flow<List<MovieTv>> =
                local.getMovieUpcoming().toFlowModels()

            override fun shouldFetch(data: List<MovieTv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieTvResponse>>> =
                remote.getMovieUpcoming()

            override suspend fun saveCallResult(data: List<MovieTvResponse>) {
                data.map {
                    local.insertMovie(it.toEntities(MovieType.UPCOMING.name))
                }
            }
        }.asFlow()

    override fun getMovieDetail(movieId: Int, movieType: String): Flow<Resource<MovieTv>> =
        object : NetworkBoundResource <MovieTv, MovieTvResponse>() {
            override fun loadFromDB(): Flow<MovieTv> =
                local.getMovieDetail(movieId).toFlowModel()

            override fun shouldFetch(data: MovieTv?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<MovieTvResponse>> =
                remote.getMovieById(movieId)

            override suspend fun saveCallResult(data: MovieTvResponse) {
                    local.insertMovie(data.toEntities(movieType))
            }
        }.asFlow()

    override fun clearData() {
        CoroutineScope(Dispatchers.IO).launch {
            local.clearMovieTvTable()
        }
    }
}