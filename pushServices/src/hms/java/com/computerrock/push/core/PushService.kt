package com.computerrock.push.core

import com.huawei.hms.push.HmsMessageService

open class PushService : HmsMessageService() {

    //internal
    final override fun onMessageReceived(p0: com.huawei.hms.push.RemoteMessage?) {
        //convert hms RemoteMessage to our own RemoteMessage
    }

    //public
    open fun onRemoteMessageReceived(remoteMessage: RemoteMessage) {
        //app overrides this
    }
}
