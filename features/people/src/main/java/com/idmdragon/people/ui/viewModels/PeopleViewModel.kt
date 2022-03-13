package com.idmdragon.people.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.idmdragon.domain.model.People
import com.idmdragon.domain.usecase.PeopleUseCase

class PeopleViewModel(private val peopleUseCase: PeopleUseCase) : ViewModel() {

    fun getPopularPeople(): LiveData<PagingData<People>> = peopleUseCase.getPopularPeople().asLiveData()

}