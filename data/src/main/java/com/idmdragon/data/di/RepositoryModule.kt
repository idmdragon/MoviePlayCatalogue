package com.idmdragon.data.di

import com.idmdragon.data.repository.MovieRepositoryImpl
import com.idmdragon.data.repository.SearchRepositoryImpl
import com.idmdragon.domain.repository.MovieRepository
import com.idmdragon.domain.repository.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(get(), get())
    }
    single<SearchRepository> {
        SearchRepositoryImpl(get())
    }
}