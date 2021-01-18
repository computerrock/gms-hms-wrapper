package com.computerrock.push.core

import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage

class HmsPushService : HmsMessageService() {

    override fun onNewToken(p0: String?) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage?) {
        super.onMessageReceived(p0)
    }
}
