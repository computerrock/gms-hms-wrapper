package com.computerrock.push.core

import com.computerrock.pushServices.PushServiceManager
import com.computerrock.pushServices.RemoteMassage
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


internal class GmsPushService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        PushServiceManager.sendNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        PushServiceManager.sendNewMessage(GmsRemoteMessage(message))
    }
}
