package br.com.maximatech.maxapp.model

import com.google.gson.annotations.SerializedName
import util.NumberUtil
import java.io.Serializable
import java.text.NumberFormat
import java.util.*

data class PedidoHolder(val pedidos:List<Pedido>):Serializable
 class Pedido:Serializable{
     var idLocal:Int = 0
     @SerializedName("numero_ped_Rca")
     var numeroPedidoRca:String = ""
     @SerializedName("numero_ped_erp")
     var numeroPedidoErp:String = ""
     var codigoCliente:String = ""
     @SerializedName("NOMECLIENTE")
     var nomeCliente:String = ""
     var data:Date? = null
     var status:String = ""
     var critica:String? = null
     var tipo:String = ""
     var legendas:List<String>? = null

     override fun toString(): String {
         if(numeroPedidoErp.isEmpty())return ""
          return NumberUtil.formatNumber(numeroPedidoRca.toLong())
              .plus(" / ").plus( NumberUtil.formatNumber(numeroPedidoErp.toLong()))
     }

     fun getCliente():String{
         return codigoCliente.plus(" - ")
             .plus(nomeCliente)
     }
 }