package com.computerrock.pushServices


interface IPushTokenService {
    fun obtainPushToken(iToken: IToken)
    fun deletePushToken()
}