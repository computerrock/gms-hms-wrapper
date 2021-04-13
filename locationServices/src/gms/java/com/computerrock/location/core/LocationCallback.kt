package com.computerrock.location.core

open class LocationCallback {

    internal val locationCallback: com.google.android.gms.location.LocationCallback = object : com.google.android.gms.location.LocationCallback() {
        override fun onLocationResult(p0: com.google.android.gms.location.LocationResult) {
            this@LocationCallback.onLocationResult(LocationResult(p0))
        }

        override fun onLocationAvailability(p0: com.google.android.gms.location.LocationAvailability) {
            this@LocationCallback.onLocationAvailability(LocationAvailability(p0))
        }
    }
    open fun onLocationResult(locationResult: LocationResult) {}

    open fun onLocationAvailability(locationAvailability: LocationAvailability) {}
}