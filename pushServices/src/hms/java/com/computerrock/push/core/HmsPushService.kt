package com.computerrock.push.core

import com.computerrock.pushServices.PushServiceManager
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage

internal class HmsPushService : HmsMessageService() {

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        token?.let { PushServiceManager.sendNewToken(it) }
    }

    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)
        message?.let { PushServiceManager.sendNewMessage(HmsRemoteMessage(message)) }
    }
}
