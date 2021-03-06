package com.idmdragon.domain.usecase

import androidx.paging.PagingData
import com.idmdragon.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    fun searchMovieTv(query: String): Flow<PagingData<Search>>
}