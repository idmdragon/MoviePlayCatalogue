package com.idmdragon.data.di

import com.idmdragon.data.repository.MovieRepositoryImpl
import com.idmdragon.data.repository.PeopleRepositoryImpl
import com.idmdragon.data.repository.SearchRepositoryImpl
import com.idmdragon.data.repository.TvRepositoryImpl
import com.idmdragon.domain.repository.MovieRepository
import com.idmdragon.domain.repository.PeopleRepository
import com.idmdragon.domain.repository.SearchRepository
import com.idmdragon.domain.repository.TvRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(get(), get())
    }
    single<SearchRepository> {
        SearchRepositoryImpl(get())
    }
    single<PeopleRepository> {
        PeopleRepositoryImpl(get())
    }
    single<TvRepository> {
        TvRepositoryImpl(get(),get())
    }
}