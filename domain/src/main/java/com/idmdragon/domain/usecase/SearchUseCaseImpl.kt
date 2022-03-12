package com.idmdragon.domain.usecase

import androidx.paging.PagingData
import com.idmdragon.domain.model.Movie
import com.idmdragon.domain.model.Search
import com.idmdragon.domain.repository.MovieRepository
import com.idmdragon.domain.repository.SearchRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class SearchUseCaseImpl(private val searchRepository: SearchRepository) : SearchUseCase {

    override fun searchMovieTv(query: String): Flow<PagingData<Search>> =
        searchRepository.searchMovieTv(query)
}