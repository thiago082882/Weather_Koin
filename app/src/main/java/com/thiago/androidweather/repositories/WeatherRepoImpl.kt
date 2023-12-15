package com.thiago.androidweather.repositories

import com.thiago.androidweather.models.BaseModel
import com.thiago.androidweather.models.DailyForecasts
import com.thiago.androidweather.models.HourlyForecast
import com.thiago.androidweather.models.Location
import com.thiago.androidweather.network.Api
import retrofit2.Response

class WeatherRepoImpl(private val api: Api):WeatherRepo {
    override suspend fun searchLocation(query: String): BaseModel<List<Location>> {
        return request {
            api.searchLocation(query = query)
        }
    }

    override suspend fun getDailyForecasts(locationKey: String): BaseModel<DailyForecasts> {
        return request {
            api.getDailyForecasts(locationKey = locationKey)
        }
    }

    override suspend fun getHourlyForecasts(locationKey: String): BaseModel<List<HourlyForecast>> {
        return request {
            api.getHourlyForecasts(locationKey = locationKey)
        }
    }
}
suspend fun<T> request(request: suspend ()-> Response<T>):BaseModel<T>{
    try {
        request().also {
            return if (it.isSuccessful){
                BaseModel.Success(it.body()!!)
            }else{
                BaseModel.Error(it.errorBody()?.string().toString())
            }
        }
    }catch (e:Exception){
        return BaseModel.Error(e.message.toString())
    }
}