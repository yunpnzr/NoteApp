package com.yunpnzr.mynoteapp.core.source.remote.model.weather


import com.google.gson.annotations.SerializedName

data class AllWeatherDataResponse(
    @SerializedName("cuaca")
    var cuaca: List<List<Cuaca?>?>?,
    @SerializedName("lokasi")
    var lokasi: Lokasi?
)