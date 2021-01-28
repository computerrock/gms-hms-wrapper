package com.computerrock.analytics

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics


object AnalyticsManager {

    fun enableLog() {

    }

    fun logEvent(context: Context, name: String, params: android.os.Bundle) {
        FirebaseAnalytics.getInstance(context).logEvent(name, params)
    }

    fun setUserID(context: Context, id: String) {
        FirebaseAnalytics.getInstance(context).setUserId(id)
    }
}