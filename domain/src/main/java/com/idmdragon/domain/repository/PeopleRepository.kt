package com.idmdragon.domain.repository

import androidx.paging.PagingData
import com.idmdragon.domain.model.People
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPopularPeople(): Flow<PagingData<People>>
    fun getPersonById(personId: Int): Flow<Resource<People>>
}