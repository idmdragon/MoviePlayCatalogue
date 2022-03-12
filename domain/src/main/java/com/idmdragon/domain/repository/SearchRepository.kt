package com.idmdragon.domain.repository

import androidx.paging.PagingData
import com.idmdragon.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchMovieTv(query: String): Flow<PagingData<Search>>
}