package com.idmdragon.search.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.idmdragon.domain.model.Search
import com.idmdragon.domain.usecase.SearchUseCase

class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    fun searchMovieTv(query: String) : LiveData<PagingData<Search>> =
        searchUseCase.searchMovieTv(query).asLiveData()
}