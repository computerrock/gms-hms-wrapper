package com.computerrock.location.core

import com.google.android.gms.location.Priority

class LocationRequest internal constructor(private val builder: com.google.android.gms.location.LocationRequest.Builder) {

    constructor() : this(com.google.android.gms.location.LocationRequest.Builder(1000))

    companion object {
        const val PRIORITY_HIGH_ACCURACY = Priority.PRIORITY_HIGH_ACCURACY
        const val PRIORITY_BALANCED_POWER_ACCURACY = Priority.PRIORITY_BALANCED_POWER_ACCURACY
        const val PRIORITY_LOW_POWER = Priority.PRIORITY_LOW_POWER
        const val PRIORITY_PASSIVE = Priority.PRIORITY_PASSIVE
    }

    fun setPriority(priority: Int): LocationRequest {
        builder.setPriority(priority)
        return this
    }

    fun setIntervalMillis(intervalMillis: Long): LocationRequest {
        builder.setIntervalMillis(intervalMillis)
        return this
    }

    fun setMaxUpdates(maxUpdates: Int): LocationRequest {
        builder.setMaxUpdates(maxUpdates)
        return this
    }

    fun setDurationMillis(durationMillis: Long): LocationRequest {
        builder.setDurationMillis(durationMillis)
        return this
    }

    fun setMinUpdateDistanceMeters(minUpdateDistanceMeters: Float): LocationRequest {
        builder.setMinUpdateDistanceMeters(minUpdateDistanceMeters)
        return this
    }

    fun getGmsLocationRequest(): com.google.android.gms.location.LocationRequest {
        return builder.build()
    }
}