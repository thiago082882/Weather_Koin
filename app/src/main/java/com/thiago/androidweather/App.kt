package com.thiago.androidweather

import android.app.Application
import com.thiago.androidweather.network.Api
import com.thiago.androidweather.repositories.WeatherRepo
import com.thiago.androidweather.repositories.WeatherRepoImpl
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(module {
                single {
                    Retrofit
                        .Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://dataservice.accuweather.com/")
                        .build()
                }
                single {
                    val retrofit:Retrofit = get()
                    retrofit.create(Api::class.java)
                }
                single {
                    val api:Api = get()
                    WeatherRepoImpl(api)
                } bind WeatherRepo::class
            })
        }
    }
}