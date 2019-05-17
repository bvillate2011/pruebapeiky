package co.prueba.peiky.Utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class AdeptAndroid : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

    }

    fun checkIfHasNetwork(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    companion object {

        var instance: AdeptAndroid? = null
            private set

        fun hasNetwork(): Boolean {
            return instance!!.checkIfHasNetwork()
        }
    }
}