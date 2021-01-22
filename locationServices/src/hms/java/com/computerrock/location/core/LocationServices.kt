package com.computerrock.location.core

import android.app.Activity
import com.huawei.hms.location.LocationServices

object LocationServices {
    fun getFusedLocationProviderClient(activity: Activity): FusedLocationProviderClient {
        return FusedLocationProviderClient(LocationServices.getFusedLocationProviderClient(activity))
    }
}
