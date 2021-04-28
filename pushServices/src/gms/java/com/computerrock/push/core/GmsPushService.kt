package com.computerrock.push.core

import android.util.Log
import com.computerrock.pushServices.ActivePushTokenListener
import com.computerrock.pushServices.PushServiceManager
import com.computerrock.pushServices.PushTokenService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


internal class GmsPushService : FirebaseMessagingService(), PushTokenService {

    private val TAG = "GmsPushService"

    init {
        PushServiceManager.setPushTokenService(this)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        PushServiceManager.sendNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        PushServiceManager.sendNewMessage(GmsRemoteMessage(message))
    }

    override fun obtainPushToken(activePushTokenListner: ActivePushTokenListener) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            activePushTokenListner.getToken(task.result)
        })
    }

    override fun deletePushToken() {
        FirebaseMessaging.getInstance().isAutoInitEnabled = false
        FirebaseMessaging.getInstance().deleteToken()
    }
}
