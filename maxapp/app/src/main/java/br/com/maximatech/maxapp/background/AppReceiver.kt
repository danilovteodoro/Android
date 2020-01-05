package br.com.maximatech.maxapp.background

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AppReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("<<AppReceiver>>","RECEIVER !!")
        context!!.startService(Intent(context!!,NotificationService::class.java))
    }

}