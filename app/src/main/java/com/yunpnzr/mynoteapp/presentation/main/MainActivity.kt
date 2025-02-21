package com.yunpnzr.mynoteapp.presentation.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yunpnzr.mynoteapp.core.utils.loadCityCode
import com.yunpnzr.mynoteapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        searchNotes()
        permissionLocation()
    }

    private fun permissionLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            checkCurrentLocation()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            checkCurrentLocation()
        }
    }

    private fun checkCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude

                    viewModel.getCoordinate(latitude, longitude)

                    lifecycleScope.launch {
                        viewModel.district.collect { district ->
                            binding.tvLocationMain.text = district
                            val cityCodeMap = loadCityCode(this@MainActivity)
                            val cityCode = cityCodeMap[district]
                            if (cityCode != null) {
                                Log.d("MainActivity", "City Code: $cityCode")
                            } else {
                                Log.d("MainActivity", "City Code not found for $district")
                            }
                        }
                    }

                    lifecycleScope.launch {
                        viewModel.locationSub.collect { locationSub ->
                            binding.tvLocationSub.text = locationSub
                        }
                    }


                }
            }
        } else {
            Toast.makeText(this, "Izin lokasi tidak diberikan", Toast.LENGTH_SHORT).show()
            finishAffinity()
        }
    }

    private fun searchNotes() {
        /*with(binding){
            svNotes.setupWithSearchBar(sbNotes)
            svNotes
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    sbNotes.setText(svNotes.text)
                    false
                }
        }*/
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }
}