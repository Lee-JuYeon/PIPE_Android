package com.cavss.pipe.util.gps

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import android.os.Looper
import androidx.lifecycle.LiveData
import com.google.android.gms.location.LocationRequest

class LocationLiveData(var context: Activity) : LiveData<MapDTO>() {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val requestPermissionCode = 1

    private fun setMapDTO(location: Location?) {
        location?.let { location ->
            value = MapDTO(location.longitude, location.latitude)
        }
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            locationResult ?: return
            for (location in locationResult.locations) {
                setMapDTO(location)
            }
        }
    }

    internal fun requestPermission(){
        try {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                requestPermissionCode
            )
        }catch (e:Exception){
            Log.e("mException", "LocationLiveData, requestPermission // Exception : ${e.message}")
        }
    }

    internal fun updateLocation() {
        try {
            if (
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("mException", "LocationLiveData, updateLocation // 권한 승낙이 안되어 있음.")
                return
            }else if (
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("mException", "LocationLiveData, updateLocation // 권한 승낙이 이미 되어 있음.")
                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
            }
        }catch (e:Exception){
            Log.e("mException", "LocationLiveData, updateLocation // Exception : ${e.message}")
        }
    }

    override fun onActive() {
        super.onActive()
        if (
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("mException", "LocationLiveData, onActive // 권한 승낙이 안되어 있음.")
            return
        }else if (
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("mException", "LocationLiveData, onActive // 권한 승낙이 이미 되어 있음.")
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location.also {
                    setMapDTO(it)
                }
            }
            updateLocation()
        }
    }

    override fun onInactive() {
        super.onInactive()
        // 위치업데이트 제거
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    companion object {
        val ONE_MINUTE : Long = 60000
        val locationRequest : LocationRequest = LocationRequest.create().apply {
            interval = ONE_MINUTE
            fastestInterval = ONE_MINUTE/4
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

}

/*
    if (
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
    ) {
        locationClient.lastLocation.addOnSuccessListener { location: Location? ->
            val currentLatitude = location?.latitude // 위도
            val currentLongitude = location?.longitude // 경도
            /*
            null이 리턴되는 경우 :
                1. 위치 정보를 얻은 적이 없을 때
                2. 디바이스의 Location 사용이 중지되는 경우 캐시 정보 삭제되기 때문에 null return
                3. google play 서비스가 재실행되었을 때, 저장된 위치 정보가 없기 때문에 null return
            */
        }
    }
    //    val requestPermissionLauncher =
//        registerForActivityResult(RequestPermission()
//        ) { isGranted: Boolean ->
//            if (isGranted) {
//                // Permission is granted. Continue the action or workflow in your
//                // app.
//            } else {
//                // Explain to the user that the feature is unavailable because the
//                // features requires a permission that the user has denied. At the
//                // same time, respect the user's decision. Don't link to system
//                // settings in an effort to convince the user to change their
//                // decision.
//            }
//        }
//    val settingResultRequest = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartIntentSenderForResult(),
//        onResult = { activityResult: ActivityResult ->
//            if (activityResult.resultCode == 0){
//                Log.e("mException", "권한 승낙됨")
//            }else{
//                Log.e("mException", "권한 거절됨")
//            }
//        }
//    )
    // call this function on button click
fun checkLocationSetting(
    context: Context,
    onDisabled: (IntentSenderRequest) -> Unit,
    onEnabled: () -> Unit
) {
    LatLng()
    val locationRequest = LocationRequest.create().apply {
        interval = 1000
        fastestInterval = 1000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    val client: SettingsClient = LocationServices.getSettingsClient(context)
    val builder: LocationSettingsRequest.Builder = LocationSettingsRequest
        .Builder()
        .addLocationRequest(locationRequest)
    val gpsSettingTask: Task<LocationSettingsResponse> =
        client.checkLocationSettings(builder.build())
    gpsSettingTask.addOnSuccessListener { onEnabled() }
    gpsSettingTask.addOnFailureListener { exception ->
        if (exception is ResolvableApiException) {
            try {
                val intentSenderRequest = IntentSenderRequest
                    .Builder(exception.resolution)
                    .build()
                onDisabled(intentSenderRequest)
            } catch (sendEx: IntentSender.SendIntentException) {
                // ignore here
            }
        }
    }
}
 */
