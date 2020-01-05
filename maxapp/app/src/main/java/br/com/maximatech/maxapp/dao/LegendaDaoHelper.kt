package br.com.maximatech.maxapp.dao

import android.content.ContentValues
import android.database.Cursor

class LegendaDaoHelper {
    companion object {
        const val TABLE = "Legenda"
        const val ID = "_id"
        const val LEGENDA = "legenda"
        const val ID_PEDIDO ="id_pedido"

        val QUERY_LEGENDAS = "SELECT " +
                "$ID, " +
                "$LEGENDA FROM $TABLE " +
                "WHERE $ID_PEDIDO = ?"

        fun toContentValues(legenda:String,idPedido:Long):ContentValues{
            val values = ContentValues()
            values.put(LEGENDA,legenda)
            values.put(ID_PEDIDO,idPedido)
            return values
        }

        fun toLegendas(cursor:Cursor):List<String>{
            val list = mutableListOf<String>()
            while (cursor.moveToNext()){
                val legenda = cursor.getString(1)
                list.add(legenda)
            }
            return list
        }
    }
}