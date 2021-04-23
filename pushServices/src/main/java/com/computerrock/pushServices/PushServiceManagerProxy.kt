package com.computerrock.pushServices

object PushServiceManagerProxy {

    @JvmStatic
    fun addObserver(observer: PushServiceObserver) {
        PushServiceManager.addObserver(observer)
    }

    @JvmStatic
    fun removeObserver(observer: PushServiceObserver) {
        PushServiceManager.removeObserver(observer)
    }

    fun deletePushToken() {
        PushServiceManager.deletePushToken()
    }

    fun obtainPushToken(iToken: IToken) {
        PushServiceManager.obtainPushToken(iToken)
    }
}