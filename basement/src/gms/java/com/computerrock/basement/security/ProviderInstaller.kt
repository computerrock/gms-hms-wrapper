package com.computerrock.basement.security

import android.content.Context
import android.content.Intent

object ProviderInstaller {
    @Throws(
        com.google.android.gms.common.GooglePlayServicesRepairableException::class,
        com.google.android.gms.common.GooglePlayServicesNotAvailableException::class
    )
    fun installIfNeeded(context: Context) {
        com.google.android.gms.security.ProviderInstaller.installIfNeeded(context)
    }

    fun installIfNeededAsync(context: Context, providerInstallListener: ProviderInstallListener) {
        com.google.android.gms.security.ProviderInstaller.installIfNeededAsync(context, object : com.google.android.gms.security.ProviderInstaller.ProviderInstallListener {
            override fun onProviderInstalled() {
                providerInstallListener.onProviderInstalled()
            }

            override fun onProviderInstallFailed(errorCode: Int, intent: Intent) {
                providerInstallListener.onProviderInstallFailed(errorCode, intent)
            }
        })
    }

    interface ProviderInstallListener {
        fun onProviderInstalled()
        fun onProviderInstallFailed(errorCode: Int, intent: Intent)
    }
}