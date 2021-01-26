package com.example.servicesapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.computerrock.analytics.AnalyticsManager
import com.computerrock.location.core.*
import com.computerrock.pushServices.PushServiceObserver
import com.computerrock.tasks.OnSuccessListener
import com.computerrock.pushServices.*


class MainActivity : AppCompatActivity(), PushServiceObserver {

    companion object {
        private const val REQUEST_CODE_PERMISSION_GPS = 1001
    }

    private val fusedLocationProviderClient by lazy {
       LocationServices.getFusedLocationProviderClient(
           this
       )
    }

    private val locationRequest by lazy {
        val locationRequest = LocationRequest()
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        locationRequest.setInterval(20000)
        locationRequest.setFastestInterval(20000)
        locationRequest.setSmallestDisplacement(1000f)
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

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE_PERMISSION_GPS
        )
        PushServiceManagerProxy.addObserver(this)

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
                        this,
                        object : OnSuccessListener<Location> {
                            override fun onSuccess(result: Location?) {
                                Log.d("MainActivity", "lastLocation: $result")
                            }
                        })
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

    override fun onRemoteMessageReceived(message: RemoteMassage) {
        TODO("Not yet implemented")
    }
}