package br.com.maximatech.maxapp.background

import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Handler
import android.os.HandlerThread
import android.os.Process
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import br.com.maximatech.maxapp.R
import br.com.maximatech.maxapp.activity.MainActivity
import br.com.maximatech.maxapp.model.Pedido
import br.com.maximatech.maxapp.service.PedidoService
import util.NetworkUtil


class NotificationService : IntentService("NotificationService") {
    private val TAG = "NotificationService"
    private  lateinit var pedidoService:PedidoService
    private   var handlerThread: HandlerThread
    private  var handler: Handler? = null

    init {
        handlerThread = HandlerThread(TAG,Process.THREAD_PRIORITY_BACKGROUND)
        handlerThread.start()
    }
    override fun onHandleIntent(intent: Intent?) {
        pedidoService = PedidoService()
        handler = Handler(handlerThread.looper)
        handler!!.post(BackgroundClass())

    }

    inner class BackgroundClass : Runnable {
        override fun run() {
            if(NetworkUtil.isNetworkConnected(this@NotificationService)){
                val pedidos = pedidoService.listPedidos()
                val index = (1 until pedidos.size).random()
                showNotification(pedidos[index])

            }
        }
    }

    fun showNotification(pedido:Pedido){
        val urSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(this,TAG)
            .setSmallIcon(R.drawable.maxima_logotipo)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
            .setContentTitle("Status do Pedidos")
            .setContentText("pedido $pedido status : ${pedido.status}")
            .setColor(this.resources.getColor(R.color.colorPrimary))
            .setAutoCancel(true)
            .setSound(urSound)
        val intent = Intent(this,MainActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra(MainActivity.IT_SCREEN,MainActivity.SCREEN.CLIENTE)

        val stackBuilder = TaskStackBuilder.create(this)
//        stackBuilder.addParentStack(DashboardActivity::class.java)
        stackBuilder.addNextIntentWithParentStack(intent)
        val paddingResultIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        builder.setContentIntent(paddingResultIntent)

        val notificationManager:NotificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(1,builder.build())

    }
}