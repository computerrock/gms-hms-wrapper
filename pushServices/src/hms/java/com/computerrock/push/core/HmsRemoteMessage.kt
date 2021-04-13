package com.computerrock.push.core

import android.os.Parcelable
import com.computerrock.pushServices.RemoteMassage

internal class HmsRemoteMessage(private val remoteMessage: com.huawei.hms.push.RemoteMessage) : RemoteMassage() {

    override fun getData(): Map<String, String> {
        return remoteMessage.dataOfMap
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T:Parcelable> getMessageOriginal(): T {
        return remoteMessage as T
    }

}