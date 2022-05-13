package com.cavss.pipe.util.internet

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData

class InternetManager(private val connectivityManager: ConnectivityManager):
    LiveData<Boolean>(){

    constructor(appContext: Application) : this(
        appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    )

    private val networkCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ConnectivityManager.NetworkCallback(){

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.e("mException", "네트워크 사용 가능함 (${network})")
            postValue(true)
        }

        // 네트워크 연결이 있는지 확인
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            val isInternet = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            Log.e("mException", "네트워크 Capabilities: ${network} $networkCapabilities")
            val isValidated = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            if (isValidated){
                Log.e("mException", "네트워크 hasCapability: ${network} $networkCapabilities")
            } else{
                Log.e("mException", "네트워크 has No Connection Capability: ${network} $networkCapabilities")
            }
            postValue(isInternet && isValidated)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            Log.e("mException", "네크워크를 잃어버림 (${network})")
            postValue(false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build(), networkCallback)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}

//class InternetManager(private val context: Context) : LiveData<NetworkStatus>() {
//
//    val valideNetworkConnections : ArrayList<Network> = ArrayList()
//    var connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback
//
//    fun announceStatus(){
//        if (valideNetworkConnections.isNotEmpty()){
//            postValue(NetworkStatus.Available)
//        } else {
//            postValue(NetworkStatus.Unavailable)
//        }
//    }
//
//    fun getConnectivityManagerCallback() =
//        object : ConnectivityManager.NetworkCallback(){
//            override fun onAvailable(network: Network) {
//                super.onAvailable(network)
//                val networkCapability = connectivityManager.getNetworkCapabilities(network)
//                val hasNetworkConnection = networkCapability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)?:false
//                if (hasNetworkConnection){
//                    determineInternetAccess(network)
//                }
//            }
//
//            override fun onLost(network: Network) {
//                super.onLost(network)
//                valideNetworkConnections.remove(network)
//                announceStatus()
//            }
//
//            override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
//                super.onCapabilitiesChanged(network, networkCapabilities)
//                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)){
//                    determineInternetAccess(network)
//                } else {
//                    valideNetworkConnections.remove(network)
//                }
//                announceStatus()
//            }
//        }
//
//    private fun determineInternetAccess(network: Network) {
//        CoroutineScope(Dispatchers.IO).launch{
//            if (InernetAvailablity.check()){
//                withContext(Dispatchers.Main){
//                    valideNetworkConnections.add(network)
//                    announceStatus()
//                }
//            }
//        }
//    }
//
//
//    override fun onActive() {
//        super.onActive()
//        connectivityManagerCallback = getConnectivityManagerCallback()
//        val networkRequest = NetworkRequest
//            .Builder()
//            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//            .build()
//        connectivityManager.registerNetworkCallback(networkRequest, connectivityManagerCallback)
//    }
//
//    override fun onInactive() {
//        super.onInactive()
//        connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
//    }
//}