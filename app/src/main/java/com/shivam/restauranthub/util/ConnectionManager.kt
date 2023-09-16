package com.shivam.restauranthub.util
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast

class ConnectionManager {

    fun isDeviceNetworkConnected(context: Context):Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager

        val activeNetwork:NetworkInfo?=connectivityManager.activeNetworkInfo
        if(activeNetwork?.isConnected!=null){
            return activeNetwork.isConnected
        }else{
            return false
        }

    }
}
