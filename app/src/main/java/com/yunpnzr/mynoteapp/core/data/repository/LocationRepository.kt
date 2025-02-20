package com.yunpnzr.mynoteapp.core.data.repository

import android.util.Log
import com.yunpnzr.mynoteapp.core.data.datasource.LocationDataSource

interface LocationRepository {
    fun setCoordinate(lat: Double, long: Double)
    fun getCoordinate(): Pair<Double, Double>
}

class LocationRepositoryImpl(
    private val locationDataSource: LocationDataSource
): LocationRepository {
    override fun setCoordinate(lat: Double, long: Double) {
        return locationDataSource.setCoordinate(lat, long)
    }

    override fun getCoordinate(): Pair<Double, Double> {
        Log.d("LocationRepositoryImpl", "Get latitude: ${locationDataSource.getCoordinate().first}, Longitude: ${locationDataSource.getCoordinate().second}")
        return locationDataSource.getCoordinate()
    }
}