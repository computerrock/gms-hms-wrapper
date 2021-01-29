package com.computerrock.pushServices


interface PushServiceObserver {
    fun onNewToken(token:String)
    fun onMessageReceived(message: RemoteMassage)
}