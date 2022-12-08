package com.computerrock.location.core

import android.content.Intent
import android.location.Location
import com.google.android.gms.location.LocationResult

class LocationResult internal constructor(private val gmsLocationResult: LocationResult) {
    val lastLocation: Location?
        get() = gmsLocationResult.lastLocation
    val locations: List<Location>
        get() = gmsLocationResult.locations

    companion object {
        fun hasResult(intent: Intent): Boolean {
            return LocationResult.hasResult(intent)
        }

        fun extractResult(intent: Intent): LocationResult? {
            return LocationResult.extractResult(intent)
        }
    }
}