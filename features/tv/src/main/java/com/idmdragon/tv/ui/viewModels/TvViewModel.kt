package com.idmdragon.tv.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.usecase.TvUseCase
import com.idmdragon.domain.utils.Resource

class TvViewModel(private val tvUseCase: TvUseCase) : ViewModel() {

    fun getTvAiringToday() : LiveData<Resource<List<MovieTv>>> =
        tvUseCase.getTvAiringToday().asLiveData()

    fun getTvOnTheAir() : LiveData<Resource<List<MovieTv>>> =
        tvUseCase.getTvOnTheAir().asLiveData()

    fun getTvTopRated() : LiveData<Resource<List<MovieTv>>> =
        tvUseCase.getTvTopRated().asLiveData()

    fun getTvPopular() : LiveData<Resource<List<MovieTv>>> =
        tvUseCase.getTvPopular().asLiveData()
}