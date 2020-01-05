package br.com.maximatech.maxapp.dao.definitions

import android.database.sqlite.SQLiteDatabase
import br.com.maximatech.maxapp.model.Pedido

interface IPedidoDao {
    fun persist(db:SQLiteDatabase,pedidos:List<Pedido>)
    fun list(db:SQLiteDatabase):List<Pedido>
    fun persistLegendas(db:SQLiteDatabase,legendas:List<String>,idPedido:Long)
    fun listLegendas(db:SQLiteDatabase,idPedido:Int):List<String>
    fun clear(db:SQLiteDatabase)
}