package com.idmdragon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.idmdragon.data.mapper.toModels
import com.idmdragon.data.source.PagingRemoteSource
import com.idmdragon.data.source.remote.SearchRemote
import com.idmdragon.data.source.remote.response.GeneralResponse
import com.idmdragon.data.source.remote.response.SearchResponse
import com.idmdragon.domain.model.Search
import com.idmdragon.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(private val remote: SearchRemote) : SearchRepository {
    override fun searchMovieTv(query: String): Flow<PagingData<Search>> =
        Pager(
            PagingConfig(10, enablePlaceholders = true, initialLoadSize = 10)
        ) {
            object : PagingRemoteSource<Search, SearchResponse>() {
                override suspend fun createCall(page: Int): GeneralResponse<SearchResponse> =
                    remote.searchMovieTv(page, query)

                override fun mapToResult(response: List<SearchResponse>): List<Search> =
                    response.toModels()
            }
        }.flow
}