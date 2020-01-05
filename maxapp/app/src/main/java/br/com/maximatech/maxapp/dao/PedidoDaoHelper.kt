package br.com.maximatech.maxapp.dao

import android.content.ContentValues
import android.database.Cursor
import androidx.core.database.getIntOrNull
import br.com.maximatech.maxapp.model.Pedido
import util.Constantes
import util.DateUtil
import java.text.DateFormat

class PedidoDaoHelper {
    companion object{
        const val ID = "_id"
        const val NUMERO_RCA= "numero_rca"
        const val NUMERO_ERP = "numero_erp"
        const val CODIGO_CLIENTE = "codigo_cliente"
        const val NOME_CLIENTE = "nome_cliente"
        const val DATA = "data"
        const val STATUS = "status"
        const val CRITICA = "critica"
        const val TIPO = "tipo"

        const val TABLE = "Pedido"

        const val QUERY_PEDIDO = "SELECT " +
                "$ID, " +
                "$NUMERO_RCA, " +
                "$NUMERO_ERP, " +
                "$CODIGO_CLIENTE, " +
                "$NOME_CLIENTE, " +
                "$DATA, " +
                "$STATUS, " +
                "$CRITICA, " +
                "$TIPO " +
                "FROM $TABLE"

        const val POS_ID =0
        const val POS_NUMERO_RCA= 1
        const val POS_NUMERO_ERP = 2
        const val POS_CODIGO_CLIENTE = 3
        const val POS_NOME_CLIENTE = 4
        const val POS_DATA = 5
        const val POS_STATUS = 6
        const val POS_CRITICA = 7
        const val POS_TIPO = 8

        fun toContentValues(pedido:Pedido):ContentValues{
            val values = ContentValues()
            values.put(NUMERO_RCA,pedido.numeroPedidoRca)
            values.put(NUMERO_ERP,pedido.numeroPedidoErp)
            values.put(CODIGO_CLIENTE,pedido.codigoCliente)
            values.put(NOME_CLIENTE,pedido.nomeCliente)
            if(pedido.data != null) values.put(DATA,
                DateUtil.format(pedido.data!!,Constantes.DATETIME_SQLITE))
            values.put(STATUS,pedido.status)
            values.put(CRITICA,pedido.critica)
            values.put(TIPO,pedido.tipo)
            return values
        }

        fun toPedidos(cursor:Cursor):List<Pedido>{
            val list:MutableList<Pedido> = mutableListOf()
            while (cursor.moveToNext()){
                val pedido = Pedido()
                pedido.idLocal = cursor.getInt(POS_ID)
                pedido.numeroPedidoRca = cursor.getString(POS_NUMERO_RCA)
                pedido.numeroPedidoErp = cursor.getString(POS_NUMERO_ERP)
                pedido.codigoCliente = cursor.getString(POS_CODIGO_CLIENTE)
                pedido.nomeCliente = cursor.getString(POS_NOME_CLIENTE)
                pedido.data = if (!cursor.isNull(POS_DATA)){
                    val dateString= cursor.getString(POS_DATA)
                    DateUtil.parse(dateString,Constantes.DATETIME_SQLITE)
                } else null
                pedido.status = cursor.getString(POS_STATUS)
                pedido.critica = if(!cursor.isNull(POS_CRITICA)) cursor.getString(POS_CRITICA) else null
                pedido.tipo = cursor.getString(POS_TIPO)
                list.add(pedido)

            }
            return list
        }
    }
}