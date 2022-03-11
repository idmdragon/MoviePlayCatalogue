package com.idmdragon.tv.di

import com.idmdragon.tv.ui.TvViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvModule = module {
    viewModel {
        TvViewModel()
    }
}

