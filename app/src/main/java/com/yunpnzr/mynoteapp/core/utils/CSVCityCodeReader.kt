package com.yunpnzr.mynoteapp.core.utils

import android.content.Context
import com.yunpnzr.mynoteapp.R

fun loadCityCode(context: Context): Map<String, String> {
    val cityCodeMap = mutableMapOf<String, String>()
    try {
        val inputStream = context.resources.openRawResource(R.raw.kode_wilayah)
        inputStream.bufferedReader().use { reader ->
            reader.forEachLine { line ->
                val parts = line.split(",")
                if (parts.size == 2) {
                    val cityCode = parts[0].trim() 
                    val cityName = parts[1].trim() 
                    cityCodeMap[cityName] = cityCode
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return cityCodeMap
}