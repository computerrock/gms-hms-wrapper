package com.computerrock.push.core

import com.computerrock.pushServices.RemoteMassage

internal class HmsRemoteMessage(private val remoteMessage: com.huawei.hms.push.RemoteMessage) : RemoteMassage() {

    override fun getData(): Map<String, String> {
        return remoteMessage.dataOfMap
    }

}