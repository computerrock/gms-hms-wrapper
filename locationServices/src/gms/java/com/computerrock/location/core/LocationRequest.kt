package com.computerrock.location.core

import com.google.android.gms.common.util.VisibleForTesting

class LocationRequest internal constructor(val gmsLocationRequest: com.google.android.gms.location.LocationRequest) {

    constructor() : this(com.google.android.gms.location.LocationRequest())

    companion object {
        const val PRIORITY_HIGH_ACCURACY = 100
        const val PRIORITY_BALANCED_POWER_ACCURACY = 102
        const val PRIORITY_LOW_POWER = 104
        const val PRIORITY_NO_POWER = 105
        @VisibleForTesting
        fun create(): LocationRequest {
            return LocationRequest(com.google.android.gms.location.LocationRequest.create())
        }
    }

    @VisibleForTesting
    fun setPriority(i: Int): LocationRequest {
        gmsLocationRequest.priority = i
        return this
    }

    val priority: Int
        get() = gmsLocationRequest.priority

    fun setInterval(l: Long): LocationRequest {
        gmsLocationRequest.interval = l
        return this
    }

    val interval: Long
        get() = gmsLocationRequest.interval

    @VisibleForTesting
    fun setMaxWaitTime(l: Long): LocationRequest {
        gmsLocationRequest.maxWaitTime = l
        return this
    }

    val maxWaitTime: Long
        get() = gmsLocationRequest.maxWaitTime

    fun setFastestInterval(l: Long): LocationRequest {
        gmsLocationRequest.fastestInterval = l
        return this
    }

    val fastestInterval: Long
        get() = gmsLocationRequest.fastestInterval
    val isFastestIntervalExplicitlySet: Boolean
        get() = gmsLocationRequest.isFastestIntervalExplicitlySet

    fun setExpirationDuration(l: Long): LocationRequest {
        gmsLocationRequest.setExpirationDuration(l)
        return this
    }

    @VisibleForTesting
    fun setExpirationTime(l: Long): LocationRequest {
        gmsLocationRequest.expirationTime = l
        return this
    }

    val expirationTime: Long
        get() = gmsLocationRequest.expirationTime

    @VisibleForTesting
    fun setNumUpdates(i: Int): LocationRequest {
        gmsLocationRequest.numUpdates = i
        return this
    }

    val numUpdates: Int
        get() = gmsLocationRequest.numUpdates

    @VisibleForTesting
    fun setSmallestDisplacement(v: Float): LocationRequest {
        gmsLocationRequest.smallestDisplacement = v
        return this
    }

    val smallestDisplacement: Float
        get() = gmsLocationRequest.smallestDisplacement
}