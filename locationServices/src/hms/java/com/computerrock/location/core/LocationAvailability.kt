package com.computerrock.location.core

import android.content.Intent

class LocationAvailability internal constructor(private val hmsLocationAvailability: com.huawei.hms.location.LocationAvailability) {
    val isLocationAvailable: Boolean
        get() = hmsLocationAvailability.isLocationAvailable

    companion object {
        fun hasLocationAvailability(intent: Intent?): Boolean {
            return LocationAvailability.hasLocationAvailability(intent)
        }

        fun extractLocationAvailability(intent: Intent?): LocationAvailability? {
            return LocationAvailability.extractLocationAvailability(intent)
        }
    }
}