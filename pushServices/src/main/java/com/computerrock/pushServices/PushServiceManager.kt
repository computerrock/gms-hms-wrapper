package com.computerrock.pushServices

internal object PushServiceManager {

    private val observers: MutableList<PushServiceObserver> = mutableListOf()
    private var iPushTokenService: IPushTokenService? = TODO()

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

    fun setIPushTokenService(iPushTokenService: IPushTokenService) {
        this.iPushTokenService = iPushTokenService
    }

    fun obtainPushToken(iToken: IToken) {
        iPushTokenService?.obtainPushToken(iToken)
    }

    fun deletePushToken() {
        iPushTokenService?.deletePushToken()
    }
}