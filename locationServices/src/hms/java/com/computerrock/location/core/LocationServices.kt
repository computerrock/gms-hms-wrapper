package com.computerrock.location.core

import android.app.Activity
import android.content.Context

object LocationServices {
    @JvmStatic
    fun getFusedLocationProviderClient(activity: Activity): FusedLocationProviderClient {
        return FusedLocationProviderClient(com.huawei.hms.location.LocationServices.getFusedLocationProviderClient(activity))
    }

    @JvmStatic
    fun getFusedLocationProviderClient(context: Context): FusedLocationProviderClient {
        return FusedLocationProviderClient(com.huawei.hms.location.LocationServices.getFusedLocationProviderClient(context))
    }
}
