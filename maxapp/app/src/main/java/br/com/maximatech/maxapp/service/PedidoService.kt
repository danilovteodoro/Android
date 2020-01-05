package br.com.maximatech.maxapp.service

import android.util.Log
import br.com.maximatech.maxapp.model.Pedido
import br.com.maximatech.maxapp.model.PedidoHolder
import br.com.maximatech.maxapp.service.definitions.IPedidoService
import com.google.gson.GsonBuilder
import util.Constantes
import util.HttpHelper
import java.lang.Exception

class PedidoService :
    IPedidoService {
    private val httpHelper: HttpHelper = HttpHelper()
    private val url = "${Constantes.BASE_URL}wjl97"
    private val TAG="pedidoservice"

    override fun listPedidos(): List<Pedido> {
        try{
            val json = httpHelper.executeGet(url)
            return jsonTopedidos(json)
        }catch (e:HttpHelper.HttpException){
            Log.e(TAG,"Errors was happened while try execute listPedidos request - ${e.message}",e)
            throw e
        }catch (e:Exception){
            Log.e(TAG,"unknown error : ${e.message}",e)
            throw e
        }
    }

    private fun jsonTopedidos(json:String):List<Pedido>{
        val gson = GsonBuilder().setDateFormat(Constantes.DATE_PATTERN)
            .create()
        val pedidoHolder: PedidoHolder = gson.fromJson(json,
            PedidoHolder::class.java)
        return pedidoHolder.pedidos
    }
}