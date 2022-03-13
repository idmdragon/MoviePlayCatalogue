package com.idmdragon.movieplay.ui.splash

import androidx.lifecycle.ViewModel
import com.idmdragon.domain.usecase.MovieUseCase

class SplashViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun clearData() = movieUseCase.clearData()

}