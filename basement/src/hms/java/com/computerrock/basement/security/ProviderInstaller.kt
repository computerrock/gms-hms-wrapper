package com.computerrock.basement.security

import android.content.Context
import android.content.Intent

object ProviderInstaller {
    fun installIfNeeded(context: Context) {

    }

    fun installIfNeededAsync(context: Context, providerInstallListener: ProviderInstallListener) {
        providerInstallListener.onProviderInstalled()
    }

    interface ProviderInstallListener {
        fun onProviderInstalled()
        fun onProviderInstallFailed(errorCode: Int, intent: Intent)
    }
}