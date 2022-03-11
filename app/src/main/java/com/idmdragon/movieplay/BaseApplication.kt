package com.idmdragon.movieplay

import android.app.Application
import com.idmdragon.data.di.databaseModule
import com.idmdragon.data.di.localSourceModule
import com.idmdragon.data.di.remoteSourceModule
import com.idmdragon.data.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()

            androidContext(this@BaseApplication)

            loadKoinModules(
                listOf(
                    retrofitModule,
                    remoteSourceModule,
                    databaseModule,
                    localSourceModule
                )
            )
        }

    }
}