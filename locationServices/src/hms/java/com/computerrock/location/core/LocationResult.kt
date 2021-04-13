package com.computerrock.location.core

import android.content.Intent
import android.location.Location
import com.huawei.hms.location.LocationResult

class LocationResult internal constructor(private val hmsLocationResult: LocationResult) {
    val lastLocation: Location
        get() = hmsLocationResult.lastLocation
    val locations: List<Location>
        get() = hmsLocationResult.locations

    companion object {
        fun hasResult(intent: Intent?): Boolean {
            return LocationResult.hasResult(intent)
        }

        fun extractResult(intent: Intent?): LocationResult {
            return LocationResult.extractResult(intent)
        }
    }
}