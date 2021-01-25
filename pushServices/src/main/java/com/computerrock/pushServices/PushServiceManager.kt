package com.computerrock.pushServices

internal object PushServiceManager {

    private val observers: MutableList<PushServiceObserver> = mutableListOf()

    fun addObserver(observer: PushServiceObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: PushServiceObserver) {
        observers.remove(observer)
    }

    fun sendNewToken(token: String) {
        observers.forEach { it.onNewToken(token) }
    }

    fun sendNewMessage(message: RemoteMassage) {
        observers.forEach { it.onMessageReceived(message) }
    }
}