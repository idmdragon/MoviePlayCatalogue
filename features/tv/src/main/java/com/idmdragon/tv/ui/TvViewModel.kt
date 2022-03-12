package com.idmdragon.tv.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.usecase.TvUseCase
import com.idmdragon.domain.utils.Resource

class TvViewModel(private val tvUseCase: TvUseCase) : ViewModel() {

    fun getTvAiringToday() : LiveData<Resource<List<MovieTv>>> =
        tvUseCase.getTvAiringToday().asLiveData()
}