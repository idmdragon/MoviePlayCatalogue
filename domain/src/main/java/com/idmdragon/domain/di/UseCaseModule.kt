package com.idmdragon.domain.di


import com.idmdragon.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> {
        MovieUseCaseImpl(get())
    }
    factory<SearchUseCase> {
        SearchUseCaseImpl(get())
    }
    factory<PeopleUseCase> {
        PeopleUseCaseImpl(get())
    }
    factory<TvUseCase> {
        TvUseCaseImpl(get())
    }
}