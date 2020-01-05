package util

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import okhttp3.internal.waitMillis

class NetworkUtil {
    companion object {
        fun isNetworkConnected(context:Context) : Boolean{
            val connectionManager =  context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectionManager.activeNetwork != null
            } else {
                val netwokInfo = connectionManager.activeNetworkInfo
                netwokInfo != null && netwokInfo.isConnected
            }

        }
    }
}