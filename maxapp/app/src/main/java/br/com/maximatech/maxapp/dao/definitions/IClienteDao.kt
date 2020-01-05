package br.com.maximatech.maxapp.dao.definitions

import android.database.sqlite.SQLiteDatabase
import br.com.maximatech.maxapp.model.Cliente
import br.com.maximatech.maxapp.model.ContatoCliente

interface IClienteDao {

    fun persist(db: SQLiteDatabase, cliente: Cliente)
    fun clear(db: SQLiteDatabase)
    fun get(db:SQLiteDatabase):Cliente?
    fun persistContato(db: SQLiteDatabase, idCliente: Long, contatos: List<ContatoCliente>)
    fun getContatos(db: SQLiteDatabase, idCliente: Long): List<ContatoCliente>?
}