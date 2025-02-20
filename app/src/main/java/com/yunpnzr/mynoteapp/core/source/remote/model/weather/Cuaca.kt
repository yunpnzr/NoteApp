package com.yunpnzr.mynoteapp.core.source.remote.model.weather


import com.google.gson.annotations.SerializedName

data class Cuaca(
    @SerializedName("analysis_date")
    var analysisDate: String?,
    @SerializedName("datetime")
    var datetime: String?,
    @SerializedName("hu")
    var hu: Int?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("local_datetime")
    var localDatetime: String?,
    @SerializedName("t")
    var t: Int?,
    @SerializedName("tcc")
    var tcc: Int?,
    @SerializedName("time_index")
    var timeIndex: String?,
    @SerializedName("tp")
    var tp: Double?,
    @SerializedName("utc_datetime")
    var utcDatetime: String?,
    @SerializedName("vs")
    var vs: Int?,
    @SerializedName("vs_text")
    var vsText: String?,
    @SerializedName("wd")
    var wd: String?,
    @SerializedName("wd_deg")
    var wdDeg: Int?,
    @SerializedName("wd_to")
    var wdTo: String?,
    @SerializedName("weather")
    var weather: Int?,
    @SerializedName("weather_desc")
    var weatherDesc: String?,
    @SerializedName("weather_desc_en")
    var weatherDescEn: String?,
    @SerializedName("ws")
    var ws: Double?
)