package com.computerrock.location.core

import android.app.PendingIntent
import android.location.Location
import android.os.Looper
import androidx.annotation.RequiresPermission
import com.computerrock.tasks.ITask
import com.computerrock.tasks.Task

class FusedLocationProviderClient internal constructor(private val gmsClient: com.google.android.gms.location.FusedLocationProviderClient) {

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun getLastLocation(): ITask<Location> {
        return Task(gmsClient.lastLocation)
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun getLocationAvailability(): ITask<LocationAvailability> {
       return Task(gmsClient.locationAvailability) { LocationAvailability(it as com.google.android.gms.location.LocationAvailability) }
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun requestLocationUpdates(
        locationRequest: LocationRequest?,
        locationCallback: LocationCallback?,
        looper: Looper?
    ): ITask<Void> {
        return Task(gmsClient.requestLocationUpdates(locationRequest?.gmsLocationRequest, locationCallback?.locationCallback, looper))
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun requestLocationUpdates(
        locationRequest: LocationRequest?,
        pendingIntent: PendingIntent?
    ): ITask<Void> {
        return Task(gmsClient.requestLocationUpdates(locationRequest?.gmsLocationRequest, pendingIntent))
    }

    fun removeLocationUpdates(locationCallback: LocationCallback?): ITask<Void> {
        return Task(gmsClient.removeLocationUpdates(locationCallback?.locationCallback))
    }

    fun removeLocationUpdates(pendingIntent: PendingIntent?): ITask<Void> {
        return Task(gmsClient.removeLocationUpdates(pendingIntent))
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun setMockMode(b: Boolean): ITask<Void> {
        return Task(gmsClient.setMockMode(b))
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun setMockLocation(location: Location?): ITask<Void> {
        return Task(gmsClient.setMockLocation(location))
    }

    fun flushLocations(): ITask<Void> {
        return Task(gmsClient.flushLocations())
    }
}