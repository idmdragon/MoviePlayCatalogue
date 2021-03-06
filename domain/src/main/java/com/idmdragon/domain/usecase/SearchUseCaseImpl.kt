package com.idmdragon.domain.usecase

import androidx.paging.PagingData
import com.idmdragon.domain.model.Search
import com.idmdragon.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCaseImpl(private val searchRepository: SearchRepository) : SearchUseCase {

    override fun searchMovieTv(query: String): Flow<PagingData<Search>> =
        searchRepository.searchMovieTv(query)
}