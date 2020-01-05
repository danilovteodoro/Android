package br.com.maximatech.maxapp.background

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AlarmeRegister : BroadcastReceiver(){
    private val TAG = "<<ALARM REGISTRED>>"
    override fun onReceive(context: Context, intent: Intent?) {
        callAlerme(context)
    }

    fun callAlerme(context: Context){
        if(checkIfisCreated(context)){
            Log.i(TAG,"Alarm is already active")
            return
        }
        val intent = Intent("CALL_SERVICE")
        val pIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val alarme = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
        val timeToGo:Long = 60*5*1000
        val timeToStart = timeToGo + Calendar.getInstance().timeInMillis
        alarme.setRepeating(AlarmManager.RTC_WAKEUP, timeToStart, timeToGo, pIntent)
        Log.i(TAG,"Alarm registered !")
    }

    fun checkIfisCreated(context:Context) : Boolean{

        val intent = Intent("CALL_SERVICE")
        return (PendingIntent.getBroadcast(context,0,intent,
            PendingIntent.FLAG_NO_CREATE) != null)
    }
}