package com.yunpnzr.mynoteapp.core.source.local.pref

import android.content.SharedPreferences
import android.util.Log

interface LocationPreference {
    fun setCoordinate(lat: Double, long: Double)
    fun getCoordinate(): Pair<Double, Double>
}

class LocationPreferenceImpl(private val sharedPreferences: SharedPreferences): LocationPreference {
    override fun setCoordinate(lat: Double, long: Double) {
        sharedPreferences.edit().apply {
            putString(LATITUDE, lat.toString())
            putString(LONGITUDE, long.toString())
            apply()
        }
        Log.d("LocationPreferenceImpl", "Set latitude: $lat, Longitude: $long")
    }

    override fun getCoordinate(): Pair<Double, Double> {
        sharedPreferences.apply {
            val latitude = getString(LATITUDE, "0.0")?.toDouble() ?: 0.0
            val longitude = getString(LONGITUDE, "0.0")?.toDouble() ?: 0.0
            Log.d("LocationPreferenceImpl", " Get latitude: $latitude, Longitude: $longitude")
            return Pair(latitude, longitude)
        }
    }

    companion object{
        const val PREF_NAME = "location_preference"

        private const val LATITUDE = "latitude"
        private const val LONGITUDE = "longitude"
    }
}