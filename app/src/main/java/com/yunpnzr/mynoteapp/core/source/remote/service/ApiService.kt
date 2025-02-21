package com.yunpnzr.mynoteapp.core.source.remote.service

import com.yunpnzr.mynoteapp.core.source.remote.model.weather.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("prakiraan-cuaca")
    suspend fun getWeather(
        @Query("adm4") district: String,
    ): WeatherResponse
}