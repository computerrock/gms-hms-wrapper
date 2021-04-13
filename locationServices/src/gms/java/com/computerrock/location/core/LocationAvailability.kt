package com.computerrock.location.core

import android.content.Intent
import com.google.android.gms.location.LocationAvailability

class LocationAvailability internal constructor(private val gmsLocationAvailability: LocationAvailability) {
    val isLocationAvailable: Boolean
        get() = gmsLocationAvailability.isLocationAvailable

    companion object {
        fun hasLocationAvailability(intent: Intent?): Boolean {
            return LocationAvailability.hasLocationAvailability(intent)
        }

        fun extractLocationAvailability(intent: Intent?): LocationAvailability? {
            return LocationAvailability.extractLocationAvailability(intent)
        }
    }
}