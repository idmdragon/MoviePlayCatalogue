package com.idmdragon.data.di

import com.idmdragon.data.repository.MovieRepositoryImpl
import com.idmdragon.domain.repository.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(get(), get())
    }
}