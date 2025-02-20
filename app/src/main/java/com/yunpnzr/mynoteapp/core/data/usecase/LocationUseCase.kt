package com.yunpnzr.mynoteapp.core.data.usecase

import android.content.Context
import android.location.Geocoder
import android.os.Build
import com.yunpnzr.mynoteapp.core.data.repository.LocationRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.util.Locale

interface LocationUseCase {
    fun setCoordinate(lat: Double, long: Double)
    suspend fun getCity(): Flow<String>
    fun getProvince(): Flow<String>
    fun getDistrict(): Flow<String>
}

class LocationUseCaseImpl(
    private val context: Context,
    private val locationRepository: LocationRepository
) : LocationUseCase {

    override fun setCoordinate(lat: Double, long: Double) {
        return locationRepository.setCoordinate(lat, long)
    }

    override suspend fun getCity(): Flow<String> {
        return flow {
            val geocoder = Geocoder(context, Locale.getDefault())

            emit("Mendeteksi lokasi...") // Emit loading state

            try {
                val city = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val result = CompletableDeferred<String>()
                    geocoder.getFromLocation(
                        locationRepository.getCoordinate().first,
                        locationRepository.getCoordinate().second,
                        1)
                    { addresses ->
                        result.complete(addresses.getOrNull(0)?.subAdminArea ?: "Kota Tidak Diketahui")
                    }
                    result.await()
                } else {
                    val addresses = geocoder.getFromLocation(
                        locationRepository.getCoordinate().first,
                        locationRepository.getCoordinate().second,
                        1)
                    addresses?.getOrNull(0)?.subAdminArea ?: "Kota Tidak Diketahui"
                }

                emit(city) // Emit hasil kota
            } catch (e: IOException) {
                emit("Kota Tidak Diketahui")
            }
        }.flowOn(Dispatchers.IO) // Pastikan berjalan di background thread

    }

    override fun getProvince(): Flow<String> {
        return flow {
            val geocoder = Geocoder(context, Locale.getDefault())

            try {
                val province = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val result = CompletableDeferred<String>()
                    geocoder.getFromLocation(
                        locationRepository.getCoordinate().first,
                        locationRepository.getCoordinate().second,
                        1)
                    { addresses ->
                        result.complete(addresses.getOrNull(0)?.adminArea ?: "Provinsi Tidak Diketahui")
                    }
                    result.await()
                } else {
                    val addresses = geocoder.getFromLocation(
                        locationRepository.getCoordinate().first,
                        locationRepository.getCoordinate().second,
                        1)
                    addresses?.getOrNull(0)?.adminArea ?: "Provinsi Tidak Diketahui"
                }

                emit(province)
            } catch (e: IOException) {
                emit("Provinsi Tidak Diketahui")
            }
        }
    }

    override fun getDistrict(): Flow<String> {
        return flow {
            val geocoder = Geocoder(context, Locale.getDefault())

            try {
                val district = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val result = CompletableDeferred<String>()
                    geocoder.getFromLocation(
                        locationRepository.getCoordinate().first,
                        locationRepository.getCoordinate().second,
                        1)
                    { addresses ->
                        result.complete(addresses.getOrNull(0)?.subLocality ?: "Kecamatan Tidak Diketahui")
                    }
                    result.await()
                } else {
                    val addresses = geocoder.getFromLocation(
                        locationRepository.getCoordinate().first,
                        locationRepository.getCoordinate().second,
                        1)
                    addresses?.getOrNull(0)?.subLocality ?: "Kecamatan Tidak Diketahui"
                }

                emit(district)
            } catch (e: IOException) {
                emit("Kecamatan Tidak Diketahui")
            }
        }
    }
}