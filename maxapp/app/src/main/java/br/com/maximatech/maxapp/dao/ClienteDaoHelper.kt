package br.com.maximatech.maxapp.dao

import android.content.ContentValues
import android.database.Cursor
import br.com.maximatech.maxapp.model.Cliente

class ClienteDaoHelper {

    companion object {
        const val ID = "_id"
        const val BAKCEND_ID= "backend_id"
        const val CODIGO = "codigo"
        const val RAZAO_SOCIAL="razao_social"
        const val NOME_FANTASIA="nome_fantasia"
        const val CNPJ="cnpj"
        const val CPF="cpf"
        const val RAMO_ATIVIDADE="ramo_atividade"
        const val ENDERECO="endereco"
        const val STATUS="status"
        const val CLIENTE_TABLE = "Cliente"

        const val POS_ID = 0
        const val POS_BAKCEND_ID=1
        const val POS_CODIGO = 2
        const val POS_RAZAO_SOCIAL=3
        const val POS_NOME_FANTASIA=4
        const val POS_CNPJ=5
        const val POS_CPF=6
        const val POS_RAMO_ATIVIDADE=7
        const val POS_ENDERECO=8
        const val POS_STATUS=9

        fun toContentValues(cliente:Cliente):ContentValues{
            val contentValues = ContentValues()
            contentValues.put(BAKCEND_ID,cliente.id)
            contentValues.put(CODIGO,cliente.codigo)
            contentValues.put(RAZAO_SOCIAL,cliente.razaoSocial)
            contentValues.put(NOME_FANTASIA,cliente.nomeFantasia)
            contentValues.put(CNPJ,cliente.cnpj)
            contentValues.put(CPF,cliente.cpf)
            contentValues.put(RAMO_ATIVIDADE,cliente.ramoAtividade)
            contentValues.put(ENDERECO,cliente.endereco)
            contentValues.put(STATUS,cliente.status)
            return contentValues
        }

        const val QUERY_FIND_CLIENTE = "" +
                "SELECT $ID, " +
                "$BAKCEND_ID, " +
                "$CODIGO, " +
                "$RAZAO_SOCIAL," +
                "$NOME_FANTASIA, " +
                "$CNPJ, " +
                "$CPF, " +
                "$RAMO_ATIVIDADE, " +
                "$ENDERECO," +
                "$STATUS " +
                "FROM $CLIENTE_TABLE"

        fun toCliente(cursor:Cursor):Cliente?{
            if(cursor.moveToFirst()){
                val cliente = Cliente()
                cliente.localId = cursor.getLong(POS_ID)
                cliente.id = cursor.getInt(POS_BAKCEND_ID)
                cliente.codigo = cursor.getString(POS_CODIGO)
                cliente.razaoSocial = cursor.getString(POS_RAZAO_SOCIAL)
                cliente.nomeFantasia = cursor.getString(POS_NOME_FANTASIA)
                cliente.cnpj = if(!cursor.isNull(POS_CNPJ)) cursor.getString(POS_CNPJ) else ""
                cliente.cpf = if(!cursor.isNull(POS_CPF)) cursor.getString(POS_CPF) else ""
                cliente.ramoAtividade = cursor.getString(POS_RAMO_ATIVIDADE)
                cliente.endereco = cursor.getString(POS_ENDERECO)
                cliente.status = cursor.getString(POS_STATUS)
                return cliente
            }
            return null

        }


    }
}