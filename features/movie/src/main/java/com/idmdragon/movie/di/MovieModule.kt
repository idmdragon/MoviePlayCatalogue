package com.idmdragon.movie.di

import com.idmdragon.movie.ui.viewModels.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    viewModel {
        MovieViewModel()
    }
}

