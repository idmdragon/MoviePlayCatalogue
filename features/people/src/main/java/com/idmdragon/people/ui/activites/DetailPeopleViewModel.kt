package com.idmdragon.people.ui.activites

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.model.People
import com.idmdragon.domain.usecase.PeopleUseCase
import com.idmdragon.domain.utils.Resource
import com.idmdragon.movieplay.constant.ConstantExtras

class DetailPeopleViewModel(private val peopleUseCase: PeopleUseCase): ViewModel() {
    var personId : Int? = null

    fun getPersonById(personId:Int): LiveData<Resource<People>> =
        peopleUseCase.getPersonById(personId).asLiveData()

    fun processIntent(intent: Intent?) {
        intent?.apply {
            personId = getIntExtra(ConstantExtras.EXTRAS_PEOPLE_ID,0)
        }
    }

}