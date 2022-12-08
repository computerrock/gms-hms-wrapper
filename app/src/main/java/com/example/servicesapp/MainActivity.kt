package com.example.servicesapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.computerrock.analytics.AnalyticsManager
import com.computerrock.basement.security.ProviderInstaller
import com.computerrock.location.core.*
import com.computerrock.pushServices.PushServiceManagerProxy
import com.computerrock.pushServices.PushServiceObserver
import com.computerrock.pushServices.RemoteMassage
import com.computerrock.pushServices.ActivePushTokenListener


class MainActivity : AppCompatActivity(), PushServiceObserver, ProviderInstaller.ProviderInstallListener {

    companion object {
        private const val REQUEST_CODE_PERMISSION_GPS = 1001
        private const val ERROR_DIALOG_REQUEST_CODE = 1002
    }

    private val fusedLocationProviderClient by lazy {
       LocationServices.getFusedLocationProviderClient(
           this
       )
    }

    private val locationRequest: LocationRequest by lazy {
        LocationRequest()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setIntervalMillis(20000)
            .setMinUpdateDistanceMeters(1000f)
    }

    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            Log.d("MainActivity", "locationResult: $locationResult")
        }

        override fun onLocationAvailability(locationAvailability: LocationAvailability) {
            Log.d(
                "MainActivity",
                "isLocationAvailable: ${locationAvailability.isLocationAvailable}"
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ProviderInstaller.installIfNeededAsync(this, this)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE_PERMISSION_GPS
        )
        PushServiceManagerProxy.addObserver(this)
        PushServiceManagerProxy.obtainPushToken(object : ActivePushTokenListener {
            override fun getToken(token: String?) {
                // Push token here...
            }
        })
        PushServiceManagerProxy.deletePushToken()

        AnalyticsManager.enableLog()
        val bundle = Bundle()
        bundle.putString("start", "app_start")
        AnalyticsManager.logEvent(this, "test", bundle)

        findViewById<Button>(R.id.buttonQr).setOnClickListener {
            startActivity(Intent(this, QRCodeActivity::class.java))
        }
    }

    @SuppressLint("MissingPermission")
    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
            )
        }
    }

    override fun onPause() {
        super.onPause()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_PERMISSION_GPS -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    fusedLocationProviderClient.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        null
                    )
                    fusedLocationProviderClient.getLastLocation().addOnSuccessListener(
                        this
                    ) { result -> Log.d("MainActivity", "lastLocation: $result") }
                }
            }
        }
    }

    override fun onNewToken(token: String) {
        Log.d("token", "New token: $token")
    }

    override fun onMessageReceived(message: RemoteMassage) {
        Log.d("New message", "New message: " + message.getData()["message"])
    }

    override fun onProviderInstalled() {
        Log.d("MainActivity", "onProviderInstalled")
    }

    override fun onProviderInstallFailed(errorCode: Int, intent: Intent) {
        Log.d("MainActivity", "onProviderInstallFailed() called with: errorCode = [$errorCode], intent = [$intent]")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ERROR_DIALOG_REQUEST_CODE) {
            ProviderInstaller.installIfNeededAsync(this, this)
        }
    }
}