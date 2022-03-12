package com.idmdragon.data.di

import com.idmdragon.data.BuildConfig
import com.idmdragon.data.source.remote.MovieRemote
import com.idmdragon.data.source.remote.PeopleRemote
import com.idmdragon.data.source.remote.SearchRemote
import com.idmdragon.data.source.remote.service.MovieService
import com.idmdragon.data.source.remote.service.PeopleService
import com.idmdragon.data.source.remote.service.SearchService
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val httpClient: OkHttpClient.Builder = OkHttpClient.Builder().apply {
    addInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val request: Request = it.request().newBuilder().addHeader("api_key", BuildConfig.API_KEY).build()
        it.proceed(request)
    }
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(httpClient.build())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val retrofitModule = module {
    single {
        retrofit.create(MovieService::class.java)
    }
    single {
        retrofit.create(SearchService::class.java)
    }
    single {
        retrofit.create(PeopleService::class.java)
    }
}

val remoteSourceModule = module {
    single {
        MovieRemote(get())
    }
    single {
        SearchRemote(get())
    }
    single {
        PeopleRemote(get())
    }
}