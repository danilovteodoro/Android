package br.com.maximatech.maxapp.dao

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import br.com.maximatech.maxapp.dao.definitions.IPedidoDao
import br.com.maximatech.maxapp.model.Pedido
import br.com.maximatech.maxapp.model.PedidoHolder

class PedidoDao :IPedidoDao{
    override fun persist(db: SQLiteDatabase, pedidos: List<Pedido>) {
       db.beginTransaction()
        for(pedido in pedidos){
            val contentValues = PedidoDaoHelper.toContentValues(pedido)
            val id = db.insert(PedidoDaoHelper.TABLE,null,contentValues)
            if(id < 1)throw SQLiteException("Error While try to persist pedido")
            if(pedido.legendas!=null)persistLegendas(db,pedido.legendas!!,id)
        }
        db.setTransactionSuccessful()
        db.endTransaction()
    }

    override fun list(db: SQLiteDatabase): List<Pedido> {
       val cursor = db.rawQuery(PedidoDaoHelper.QUERY_PEDIDO,null)
        val pedidos = PedidoDaoHelper.toPedidos(cursor)
        for(pedido in pedidos){
            pedido.legendas = listLegendas(db,pedido.idLocal)
        }
        return pedidos
    }

    override fun persistLegendas(db: SQLiteDatabase, legendas: List<String>, idPedido:Long) {
        for(legenda in legendas){
            val contentValues = LegendaDaoHelper.toContentValues(legenda,idPedido)
            db.insert(LegendaDaoHelper.LEGENDA,null,contentValues)
        }
    }

    override fun clear(db: SQLiteDatabase) {
        db.beginTransaction()
        db.delete(PedidoDaoHelper.TABLE,null,null)
        db.delete(LegendaDaoHelper.TABLE,null,null)
        db.setTransactionSuccessful()
        db.endTransaction()
    }

    override fun listLegendas(db: SQLiteDatabase,idPedido:Int): List<String> {
        val cursor = db.rawQuery(LegendaDaoHelper.QUERY_LEGENDAS, arrayOf(idPedido.toString()))
        return LegendaDaoHelper.toLegendas(cursor)
    }
}