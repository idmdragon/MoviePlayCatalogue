package com.idmdragon.domain.usecase

import androidx.paging.PagingData
import com.idmdragon.domain.model.People
import com.idmdragon.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow

class PeopleUseCaseImpl(private val peopleRepository: PeopleRepository) : PeopleUseCase {

    override fun getPopularPeople(): Flow<PagingData<People>> =
        peopleRepository.getPopularPeople()

}