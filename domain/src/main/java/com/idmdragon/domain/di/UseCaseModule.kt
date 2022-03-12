package com.idmdragon.domain.di


import com.idmdragon.domain.usecase.MovieUseCase
import com.idmdragon.domain.usecase.MovieUseCaseImpl
import com.idmdragon.domain.usecase.SearchUseCase
import com.idmdragon.domain.usecase.SearchUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> {
        MovieUseCaseImpl(get())
    }
    factory<SearchUseCase> {
        SearchUseCaseImpl(get())
    }
}