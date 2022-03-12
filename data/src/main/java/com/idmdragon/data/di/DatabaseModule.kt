package com.idmdragon.data.di

import androidx.room.Room
import com.idmdragon.data.source.local.MovieLocal
import com.idmdragon.data.source.local.MoviePlayDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MoviePlayDatabase::class.java,
            "MoviePlay.db"
        ).fallbackToDestructiveMigration().build()
    }
    factory {
        get<MoviePlayDatabase>().movieDao()
    }
}

val localSourceModule = module {
    single {
        MovieLocal( get())
    }

}