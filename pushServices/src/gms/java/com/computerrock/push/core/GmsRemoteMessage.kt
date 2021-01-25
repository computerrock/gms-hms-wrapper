package com.computerrock.push.core

import com.computerrock.pushServices.RemoteMassage
import com.google.firebase.messaging.RemoteMessage

internal class GmsRemoteMessage(private val remoteMessage: RemoteMessage) : RemoteMassage() {

    override fun getData(): Map<String, String> {
        return remoteMessage.data
    }

}