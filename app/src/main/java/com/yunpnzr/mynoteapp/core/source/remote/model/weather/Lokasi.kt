package com.yunpnzr.mynoteapp.core.source.remote.model.weather


import com.google.gson.annotations.SerializedName

data class Lokasi(
    @SerializedName("adm1")
    var adm1: String?,
    @SerializedName("adm2")
    var adm2: String?,
    @SerializedName("adm3")
    var adm3: String?,
    @SerializedName("adm4")
    var adm4: String?,
    @SerializedName("desa")
    var desa: String?,
    @SerializedName("kecamatan")
    var kecamatan: String?,
    @SerializedName("kotkab")
    var kotkab: String?,
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("lon")
    var lon: Double?,
    @SerializedName("provinsi")
    var provinsi: String?,
    @SerializedName("timezone")
    var timezone: String?,
    @SerializedName("type")
    var type: String?
)