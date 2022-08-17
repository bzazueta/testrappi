package com.example.myrappi.ui.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class Conexion {

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED
        ) {
            //we are connected to a network
            true
        } else {
            false
        }
    }
}