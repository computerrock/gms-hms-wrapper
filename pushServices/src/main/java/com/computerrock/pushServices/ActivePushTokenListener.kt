package com.computerrock.pushServices


interface ActivePushTokenListener {
    fun getToken(token: String?)
}