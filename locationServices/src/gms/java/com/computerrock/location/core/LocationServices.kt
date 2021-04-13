package com.computerrock.location.core

import android.app.Activity
import android.content.Context

object LocationServices {
    @JvmStatic
    fun getFusedLocationProviderClient(activity: Activity): FusedLocationProviderClient {
        return FusedLocationProviderClient(com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(activity))
    }

    @JvmStatic
    fun getFusedLocationProviderClient(context: Context): FusedLocationProviderClient {
        return FusedLocationProviderClient(com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(context))
    }
}
