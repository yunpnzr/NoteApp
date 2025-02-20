package com.yunpnzr.mynoteapp.core.source.remote.model.weather


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("data")
    var `data`: List<AllWeatherDataResponse>?,
    @SerializedName("lokasi")
    var lokasi: LokasiX?
)