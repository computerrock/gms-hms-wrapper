package com.computerrock.push.core

import com.google.firebase.messaging.FirebaseMessagingService

open class PushService : FirebaseMessagingService() {

    //internal
    final override fun onMessageReceived(p0: com.google.firebase.messaging.RemoteMessage) {
        //convert hms RemoteMessage to our own RemoteMessage
    }

    //public
    open fun onRemoteMessageReceived(remoteMessage: RemoteMessage) {
        //app overrides this
    }
}
