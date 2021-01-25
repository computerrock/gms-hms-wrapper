package com.computerrock.pushServices

object PushServiceManagerProxy {

    fun addObserver(observer: PushServiceObserver) {
        PushServiceManager.addObserver(observer)
    }

    fun removeObserver(observer: PushServiceObserver) {
        PushServiceManager.removeObserver(observer)
    }
}