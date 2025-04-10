package com.psg.Core.Network

import com.psg.Presentation.AllOption.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {



    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
    ): WeatherResponse

}