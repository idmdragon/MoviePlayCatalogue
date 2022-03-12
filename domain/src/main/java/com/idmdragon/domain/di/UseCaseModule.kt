package com.idmdragon.domain.di


import com.idmdragon.domain.usecase.MovieUseCase
import com.idmdragon.domain.usecase.MovieUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> {
        MovieUseCaseImpl(get())
    }

}