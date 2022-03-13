package com.idmdragon.people.di

import com.idmdragon.people.ui.viewModels.DetailPeopleViewModel
import com.idmdragon.people.ui.viewModels.PeopleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val peopleModule = module {
    viewModel {
        PeopleViewModel(get())
    }
    viewModel {
        DetailPeopleViewModel(get())
    }
}

