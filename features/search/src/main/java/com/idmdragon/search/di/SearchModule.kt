package com.idmdragon.search.di

import com.idmdragon.search.ui.viewModels.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    viewModel {
        SearchViewModel(get())
    }
}

