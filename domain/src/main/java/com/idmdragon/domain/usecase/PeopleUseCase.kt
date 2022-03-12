package com.idmdragon.domain.usecase

import androidx.paging.PagingData
import com.idmdragon.domain.model.People
import kotlinx.coroutines.flow.Flow

interface PeopleUseCase {
    fun getPopularPeople(): Flow<PagingData<People>>
}