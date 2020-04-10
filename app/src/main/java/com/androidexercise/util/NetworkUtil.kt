package com.androidexercise.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    // Function to check Network Connection
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            true
        } else {
            false
        }
    }
}