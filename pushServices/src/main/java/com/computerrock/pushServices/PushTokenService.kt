package com.computerrock.pushServices


interface PushTokenService {
    fun obtainPushToken(activePushTokenListener: ActivePushTokenListener)
    fun deletePushToken()
}