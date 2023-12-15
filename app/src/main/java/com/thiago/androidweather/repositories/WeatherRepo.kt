package com.thiago.androidweather.repositories

import com.thiago.androidweather.models.BaseModel
import com.thiago.androidweather.models.DailyForecasts
import com.thiago.androidweather.models.HourlyForecast
import com.thiago.androidweather.models.Location

interface WeatherRepo {
    suspend fun searchLocation(query:String): BaseModel<List<Location>>
    suspend fun getDailyForecasts(locationKey:String):BaseModel<DailyForecasts>
    suspend fun getHourlyForecasts(locationKey: String):BaseModel<List<HourlyForecast>>
}