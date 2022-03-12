package com.idmdragon.domain.repository

import androidx.paging.PagingData
import com.idmdragon.domain.model.Movie
import com.idmdragon.domain.model.People
import com.idmdragon.domain.model.Search
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPopularPeople(): Flow<PagingData<People>>
}