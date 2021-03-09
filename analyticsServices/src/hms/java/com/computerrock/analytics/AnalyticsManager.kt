package com.computerrock.analytics

import android.content.Context
import com.huawei.hms.analytics.HiAnalytics
import com.huawei.hms.analytics.HiAnalyticsTools

object AnalyticsManager {

    fun enableLog() {
        HiAnalyticsTools.enableLog()
    }

    fun logEvent(context: Context, name: String, params: android.os.Bundle?) {
        HiAnalytics.getInstance(context).onEvent(name, params)
    }

    fun setUserID(context: Context, id: String) {
        HiAnalytics.getInstance(context).setUserId(id)
    }
}