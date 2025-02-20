package com.yunpnzr.mynoteapp.core.data.datasource

import com.yunpnzr.mynoteapp.core.source.local.pref.LocationPreference

interface LocationDataSource {
    fun setCoordinate(lat: Double, long: Double)
    fun getCoordinate(): Pair<Double, Double>
}

class LocationDataSourceImpl(
    private val locationPreference: LocationPreference
): LocationDataSource {
    override fun setCoordinate(lat: Double, long: Double){
        return locationPreference.setCoordinate(lat, long)
    }

    override fun getCoordinate(): Pair<Double, Double> {
        return locationPreference.getCoordinate()
    }
}