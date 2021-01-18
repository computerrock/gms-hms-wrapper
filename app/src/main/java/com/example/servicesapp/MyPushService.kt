package com.example.servicesapp

import com.computerrock.push.core.PushService
import com.computerrock.push.core.RemoteMessage

class MyPushService : PushService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onRemoteMessageReceived(remoteMessage: RemoteMessage) {
        super.onRemoteMessageReceived(remoteMessage)
    }
}