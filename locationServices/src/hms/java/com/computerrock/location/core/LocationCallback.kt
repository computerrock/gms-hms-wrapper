package com.computerrock.location.core

open class LocationCallback {

    internal val locationCallback: com.huawei.hms.location.LocationCallback = object : com.huawei.hms.location.LocationCallback() {
        override fun onLocationResult(locationResult: com.huawei.hms.location.LocationResult) {
            this@LocationCallback.onLocationResult(LocationResult(locationResult))
        }

        override fun onLocationAvailability(locationAvailability: com.huawei.hms.location.LocationAvailability) {
            this@LocationCallback.onLocationAvailability(LocationAvailability(locationAvailability))
        }
    }
    open fun onLocationResult(locationResult: LocationResult) {}

    open fun onLocationAvailability(locationAvailability: LocationAvailability) {}
}