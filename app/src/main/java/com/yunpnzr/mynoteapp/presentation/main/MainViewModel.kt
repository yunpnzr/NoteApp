package com.yunpnzr.mynoteapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunpnzr.mynoteapp.core.data.usecase.LocationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val convertLocationUseCase: LocationUseCase
): ViewModel() {

    private val _city = MutableStateFlow("Mendeteksi lokasi...")
    val city: StateFlow<String> = _city
    private val _province = MutableStateFlow("")
    val province: StateFlow<String> = _province
    private val _district = MutableStateFlow("")
    val district: StateFlow<String> = _district

    val locationSub: StateFlow<String> = combine(city, province) { district, province ->
        "$district, $province"
    }.stateIn(viewModelScope, SharingStarted.Lazily, "Mendeteksi lokasi...")

    init {
        getCity()
        getProvince()
        getDistrict()
    }

    fun getCoordinate(lat: Double, long: Double){
        return convertLocationUseCase.setCoordinate(lat, long)
    }

    private fun getCity() {
        viewModelScope.launch {
            convertLocationUseCase.getCity().collect { result ->
                _city.value = result
            }
        }
    }

    private fun getProvince() {
        viewModelScope.launch {
            convertLocationUseCase.getProvince().collect { result ->
                _province.value = result
            }
        }
    }

    private fun getDistrict() {
        viewModelScope.launch {
            convertLocationUseCase.getDistrict().collect { result ->
                _district.value = result
            }
        }
    }
}