package br.com.maximatech.maxapp.service

import android.util.Log
import br.com.maximatech.maxapp.model.Cliente
import br.com.maximatech.maxapp.model.ClienteHolder
import br.com.maximatech.maxapp.service.definitions.IClienteService
import com.google.gson.GsonBuilder
import util.Constantes
import util.HttpHelper
import java.lang.Exception

class ClienteService  :
    IClienteService {
    private val httpHelper:HttpHelper = HttpHelper()
    private val url = "${Constantes.BASE_URL}1bo7qj"
    private val TAG="clienteService"

    override fun getCliente(): Cliente {
        try {
            val json = httpHelper.executeGet(url)
            return jsonToCliente(json)
        }catch (e:HttpHelper.HttpException){
            Log.e(TAG,"Errors was happened while try execute getCliente request - ${e.message}",e)
            throw e
        }catch (e:Exception){
            Log.e(TAG,"unknown error : ${e.message}",e)
            throw e
        }
    }

    private fun jsonToCliente(json:String) : Cliente {
        val gson = GsonBuilder().setDateFormat(Constantes.DATE_PATTERN)
            .create()
        val holder: ClienteHolder = gson.fromJson(json,
            ClienteHolder::class.java)
        return holder.cliente
    }
}