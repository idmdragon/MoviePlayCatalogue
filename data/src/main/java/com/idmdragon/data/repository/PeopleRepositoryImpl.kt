package com.idmdragon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.idmdragon.data.mapper.toModels
import com.idmdragon.data.source.PagingRemoteSource
import com.idmdragon.data.source.remote.PeopleRemote
import com.idmdragon.data.source.remote.SearchRemote
import com.idmdragon.data.source.remote.response.GeneralResponse
import com.idmdragon.data.source.remote.response.PeopleResponse
import com.idmdragon.data.source.remote.response.SearchResponse
import com.idmdragon.domain.model.People
import com.idmdragon.domain.model.Search
import com.idmdragon.domain.repository.PeopleRepository
import com.idmdragon.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class PeopleRepositoryImpl(private val remote: PeopleRemote) : PeopleRepository {

    override fun getPopularPeople(): Flow<PagingData<People>> =
        Pager(
            PagingConfig(10, enablePlaceholders = true, initialLoadSize = 10)
        ) {
            object : PagingRemoteSource<People, PeopleResponse>() {
                override suspend fun createCall(page: Int): GeneralResponse<PeopleResponse> =
                    remote.getPopularPeople(page)

                override fun mapToResult(response: List<PeopleResponse>): List<People> =
                    response.toModels()
            }
        }.flow
}