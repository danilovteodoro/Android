package br.com.maximatech.maxapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {

    private val SQL_CREATE_CLIENTE = "CREATE TABLE Cliente(" +
            "_id INTEGER PRIMARY KEY, " +
            "backend_id INTENGER, " +
            "codigo TEXT, " +
            "razao_social TEXT, " +
            "nome_fantasia TEXT, " +
            "cnpj TEXT, " +
            "cpf TEXT, " +
            "ramo_atividade TEXT, " +
            "endereco TEXT, " +
            "status TEXT" +
            ");"
    private val SQL_CREATE_CONTATO = "CREATE TABLE ContatoCliente(" +
            "_id INTEGER PRIMARY KEY, " +
            "nome TEXT, " +
            "telefone TEXT, " +
            "celular TEXT, " +
            "conjuge TEXT, " +
            "tipo TEXT, " +
            "time TEXT, " +
            "email TEXT, " +
            "data_nascimento DATETIME, " +
            "data_nascimento_conjuge DATETIME, " +
            "id_cliente INTEGER, " +
            "FOREIGN KEY(id_cliente) REFERENCES Cliente(_id)" +
            ");"
    private val SQL_CREATE_PEDIDO = "CREATE TABLE Pedido(" +
            "_id INTEGER PRIMARY KEY, " +
            "numero_rca INTEGER, " +
            "numero_erp TEXT, " +
            "codigo_cliente TEXT, " +
            "nome_cliente TEXT, " +
            "data DATETIME, " +
            "status TEXT, " +
            "critica TEXT, " +
            "tipo TEXT" +
            ");"
    private val SQL_CREATE_LEGENDA = "CREATE TABLE Legenda(" +
            "_id INTEGER PRIMARY KEY, " +
            "legenda TEXT, " +
            "id_pedido INTEGER, " +
            "FOREIGN KEY(id_pedido) REFERENCES Pedido(_id)" +
            ");"

    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL(SQL_CREATE_CLIENTE)
        database.execSQL(SQL_CREATE_CONTATO)
        database.execSQL(SQL_CREATE_PEDIDO)
        database.execSQL(SQL_CREATE_LEGENDA)
        Log.i(TAG,"database has been created !")
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object{
        private const val DB_NAME = "maxApp.db"
        private const val DB_VERSION = 1
        private const val TAG = "DATABASE_HELER"
    }


}