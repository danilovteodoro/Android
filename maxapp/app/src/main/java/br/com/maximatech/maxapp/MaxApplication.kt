package br.com.maximatech.maxapp

import android.app.Application
import android.content.Intent

class MaxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        sendBroadcast(Intent("REGISTER_ALARM"))
    }
}