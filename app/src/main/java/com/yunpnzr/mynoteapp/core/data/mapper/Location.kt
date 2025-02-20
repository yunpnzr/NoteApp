package com.yunpnzr.mynoteapp.core.data.mapper

import android.location.Location
import com.yunpnzr.mynoteapp.core.data.model.LocationNote

fun Location?.toLocation() =
    LocationNote(
        lat = this?.latitude ?: 0.0,
        long = this?.longitude ?: 0.0
    )
