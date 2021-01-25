package com.computerrock.location.core

import android.app.PendingIntent
import android.location.Location
import android.os.Looper
import androidx.annotation.RequiresPermission
import com.computerrock.tasks.ITask
import com.computerrock.tasks.Task
import com.huawei.hms.location.FusedLocationProviderClient

class FusedLocationProviderClient internal constructor(private val hmsClient: FusedLocationProviderClient) {

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun getLastLocation(): ITask<Location> {
        return Task(hmsClient.lastLocation)
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun getLocationAvailability(): ITask<LocationAvailability> {
       return Task(hmsClient.locationAvailability) { LocationAvailability(it as com.huawei.hms.location.LocationAvailability) }
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun requestLocationUpdates(
        locationRequest: LocationRequest?,
        locationCallback: LocationCallback?,
        looper: Looper?
    ): ITask<Void> {
        return Task(hmsClient.requestLocationUpdates(locationRequest?.gmsLocationRequest, locationCallback?.locationCallback, looper))
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun requestLocationUpdates(
        locationRequest: LocationRequest?,
        pendingIntent: PendingIntent?
    ): ITask<Void> {
        return Task(hmsClient.requestLocationUpdates(locationRequest?.gmsLocationRequest, pendingIntent))
    }

    fun removeLocationUpdates(locationCallback: LocationCallback?): ITask<Void> {
        return Task(hmsClient.removeLocationUpdates(locationCallback?.locationCallback))
    }

    fun removeLocationUpdates(pendingIntent: PendingIntent?): ITask<Void> {
        return Task(hmsClient.removeLocationUpdates(pendingIntent))
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun setMockMode(b: Boolean): ITask<Void> {
        return Task(hmsClient.setMockMode(b))
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun setMockLocation(location: Location?): ITask<Void> {
        return Task(hmsClient.setMockLocation(location))
    }

    fun flushLocations(): ITask<Void> {
        return Task(hmsClient.flushLocations())
    }
}