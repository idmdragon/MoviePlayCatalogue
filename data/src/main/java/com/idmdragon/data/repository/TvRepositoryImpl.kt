package com.idmdragon.data.repository

import com.idmdragon.data.mapper.toEntities
import com.idmdragon.data.mapper.toFlowModels
import com.idmdragon.data.source.NetworkBoundResource
import com.idmdragon.data.source.local.MovieLocal
import com.idmdragon.data.source.local.TvLocal
import com.idmdragon.data.source.remote.TvRemote
import com.idmdragon.data.source.remote.response.ApiResponse
import com.idmdragon.data.source.remote.response.MovieTvResponse
import com.idmdragon.data.utils.TvType
import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.repository.TvRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class TvRepositoryImpl(
    private val remote: TvRemote,
    private val local: TvLocal
) : TvRepository {

    override fun getTvAiringToday(): Flow<Resource<List<MovieTv>>> =
        object : NetworkBoundResource<List<MovieTv>, List<MovieTvResponse>>() {
            override fun loadFromDB(): Flow<List<MovieTv>> =
                local.getTvAiringToday().toFlowModels()

            override fun shouldFetch(data: List<MovieTv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieTvResponse>>> =
                remote.getTvAiringToday()

            override suspend fun saveCallResult(data: List<MovieTvResponse>) {
                data.map {
                    local.insertTv(it.toEntities(TvType.TV_AIRING_TODAY.name))
                }
            }
        }.asFlow()

    override fun getTvOnTheAir(): Flow<Resource<List<MovieTv>>> {
        TODO("Not yet implemented")
    }

    override fun getTvTopRated(): Flow<Resource<List<MovieTv>>> {
        TODO("Not yet implemented")
    }

    override fun getTvPopular(): Flow<Resource<List<MovieTv>>> {
        TODO("Not yet implemented")
    }

    override fun getDetailTv(tvId: Int, tvType: String): Flow<Resource<MovieTv>> {
        TODO("Not yet implemented")
    }

}