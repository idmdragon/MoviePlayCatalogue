package com.idmdragon.movieplay.di

import com.idmdragon.movieplay.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel {
        SplashViewModel(get())
    }
}

