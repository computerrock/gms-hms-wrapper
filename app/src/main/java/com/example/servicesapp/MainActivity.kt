package com.example.servicesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.computerrock.pushServices.*


class MainActivity : AppCompatActivity(), PushServiceObserver {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PushServiceManagerProxy.addObserver(this)
    }

    override fun onNewToken(token: String) {
        Log.d("token", "New token: $token")
    }

    override fun onMessageReceived(message: RemoteMassage) {
        Log.d("New message", "New message: " + message.getData()["message"])
    }

    override fun onRemoteMessageReceived(message: RemoteMassage) {
        TODO("Not yet implemented")
    }

}