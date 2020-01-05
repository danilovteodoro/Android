package br.com.maximatech.maxapp.activity

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.maximatech.maxapp.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.inflater_dashboard.*
import util.NetworkUtil

class DashboardActivity : AppCompatActivity() {

    companion object {
        fun start(activity:Activity){
            val it = Intent(activity,DashboardActivity::class.java)
            activity.startActivity(it)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        checkNetworkStatus()
//        callAlerme()

        cardCliente.setOnClickListener {
                MainActivity.start(this,MainActivity.SCREEN.CLIENTE)
        }



    }



    fun checkNetworkStatus():Boolean{
        val isConnected = NetworkUtil.isNetworkConnected(this)
        if(isConnected){
            imgConnected.setImageResource(R.drawable.ic_maxima_nuvem_conectado)
        }else{
            imgConnected.setImageResource(R.drawable.ic_maxima_nuvem_desconectado)
        }
        return isConnected
    }

}