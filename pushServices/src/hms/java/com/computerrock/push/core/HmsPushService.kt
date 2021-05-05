package com.computerrock.push.core

import android.util.Log
import com.computerrock.pushServices.ActivePushTokenListener
import com.computerrock.pushServices.PushServiceManager
import com.computerrock.pushServices.PushTokenService
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.common.ApiException
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage

internal class HmsPushService : HmsMessageService(), PushTokenService {

    private val TAG = "HmsPushService"

    init {
        PushServiceManager.setPushTokenService(this)
    }

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        token?.let { PushServiceManager.sendNewToken(it) }
    }

    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)
        message?.let { PushServiceManager.sendNewMessage(HmsRemoteMessage(message)) }
    }

    /**
     * Refering to: [HMS Obtain and delete push token](https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides/android-client-dev-0000001050042041)
     */
    override fun obtainPushToken(activePushTokenListner: ActivePushTokenListener) {
        // Create a thread.
        object : Thread() {
            override fun run() {
                try {
                    // Obtain the app ID from the agconnect-service.json file.
                    val appId = AGConnectServicesConfig.fromContext(applicationContext)
                            .getString("client/app_id")

                    // Set tokenScope to HCM.
                    val tokenScope = "HCM"
                    val token =
                        HmsInstanceId.getInstance(applicationContext).getToken(appId, tokenScope)

                    activePushTokenListner.getToken(token)
                } catch (e: ApiException) {
                    Log.e(TAG, "Fetching push token failed: $e")
                }
            }
        }.start()
    }

    override fun deletePushToken() {
        // Create a thread.
        object : Thread() {
            override fun run() {
                try {
                    // Obtain the app ID from the agconnect-service.json file.
                    val appId = AGConnectServicesConfig.fromContext(applicationContext)
                        .getString("client/app_id")

                    // Set tokenScope to HCM.
                    val tokenScope = "HCM"

                    // Delete the token.
                    HmsInstanceId.getInstance(applicationContext).deleteToken(appId, tokenScope)
                } catch (e: ApiException) {
                    Log.e(TAG, "Deleting push token failed, $e")
                }
            }
        }.start()
    }
}
