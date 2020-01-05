package br.com.maximatech.maxapp.dao

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import br.com.maximatech.maxapp.dao.definitions.IClienteDao
import br.com.maximatech.maxapp.model.Cliente
import br.com.maximatech.maxapp.model.ContatoCliente

class ClienteDao : IClienteDao {
    private val TAG ="clienteDao"

    override fun persist(db: SQLiteDatabase,cliente:Cliente) {
        db.beginTransaction()
        val contentValues = ClienteDaoHelper.toContentValues(cliente)
        val idCliente = db.insert(ClienteDaoHelper.CLIENTE_TABLE,null,contentValues)
        if(idCliente < 1) throw SQLiteException("Error while try to insert")
        if(cliente.contatos!=null)
            persistContato(db,idCliente,cliente.contatos!!)
        db.setTransactionSuccessful()
        db.endTransaction()

    }

    override fun get(db: SQLiteDatabase):Cliente? {
        val cursor = db.rawQuery(ClienteDaoHelper.QUERY_FIND_CLIENTE,null)
        val cliente = ClienteDaoHelper.toCliente(cursor)
        cursor.close()
        if(cliente == null) return null
        cliente!!.contatos = getContatos(db,cliente!!.localId)
        return cliente

    }

    override fun clear(db: SQLiteDatabase) {
        db.beginTransaction()
        db.delete(ClienteDaoHelper.CLIENTE_TABLE,null,null)
        clearContatos(db)
        db.setTransactionSuccessful()
        db.endTransaction()
    }

    override fun persistContato(db:SQLiteDatabase, idCliente:Long, contatos:List<ContatoCliente>){
        for(contao in contatos){
            val contentValues = ContatoDaoHelper.toContentValues(contao,idCliente)
            db.insert("ContatoCliente",null,contentValues)
        }
    }

    fun clearContatos(db:SQLiteDatabase){
        db.delete(ContatoDaoHelper.CONTATO_TABLE,null,null)
    }

    override fun getContatos(db:SQLiteDatabase, idCliente:Long):List<ContatoCliente>?{
        val cursor = db.rawQuery(ContatoDaoHelper.QUERY_CONTATOS, arrayOf(idCliente.toString()))
        return ContatoDaoHelper.toContatoList(cursor)
    }
}