package br.com.maximatech.maxapp.dao

import android.content.ContentValues
import android.database.Cursor
import br.com.maximatech.maxapp.model.ContatoCliente
import util.Constantes
import util.DateUtil

class ContatoDaoHelper {

    companion object{
        const val CONTATO_TABLE = "ContatoCliente"
        const val ID = "_id"
        const val NOME = "nome"
        const val TELEFONE = "telefone"
        const val CELULAR = "celular"
        const val CONJUGE = "conjuge"
        const val TIPO = "tipo"
        const val TIME = "time"
        const val EMAIL = "email"
        const val DATA_NASCIMENTO = "data_nascimento"
        const val DATA_NASCIMENTO_CONJUGE = "data_nascimento_conjuge"
        const val ID_CLIENTE = "id_cliente"


        const val QUERY_CONTATOS = "SELECT " +
                "$ID, " +
                "$NOME, " +
                "$TELEFONE, " +
                "$CELULAR, " +
                "$CONJUGE, " +
                "$TIPO, " +
                "$TIME, " +
                "$EMAIL, " +
                "$DATA_NASCIMENTO, " +
                "$DATA_NASCIMENTO_CONJUGE " +
                "FROM $CONTATO_TABLE WHERE $ID_CLIENTE = ?"

        const val POS_ID = 0
        const val POS_NOME = 1
        const val POS_TELEFONE = 2
        const val POS_CELULAR = 3
        const val POS_CONJUGE = 4
        const val POS_TIPO = 5
        const val POS_TIME = 6
        const val POS_EMAIL = 7
        const val POS_DATA_NASCIMENTO = 8
        const val POS_DATA_NASCIMENTO_CONJUGE = 9
        const val POS_ID_CLIENTE = 10

        fun toContentValues(contato:ContatoCliente,clienteId:Long) : ContentValues{
            val contentValues = ContentValues()
            contentValues.put(NOME,contato.nome)
            contentValues.put(TELEFONE,contato.telefone)
            contentValues.put(CELULAR,contato.celular)
            if (!contato.conjuge.isNullOrEmpty())
                contentValues.put(CONJUGE,contato.conjuge)
            contentValues.put(TIPO,contato.tipo)
            contentValues.put(TIME,contato.time)
            contentValues.put(EMAIL,contato.email)
            if(contato.dataNascimento != null){
                contentValues.put(DATA_NASCIMENTO,DateUtil
                    .format(contato.dataNascimento!!,Constantes.DATETIME_SQLITE))
            }
            if(contato.dataNascimentoConjuge != null){
                contentValues.put(
                    DATA_NASCIMENTO_CONJUGE,DateUtil
                    .format(contato.dataNascimentoConjuge!!,Constantes.DATETIME_SQLITE))
            }
            contentValues.put(ID_CLIENTE,clienteId)
            return contentValues

        }

        fun toContatoList(cursor: Cursor):List<ContatoCliente>{
            var list = mutableListOf<ContatoCliente>()
            while (cursor.moveToNext()){
                val contato = ContatoCliente()
                contato.id = cursor.getInt(POS_ID)
                contato.nome = cursor.getString(POS_NOME)
                contato.telefone = if(!cursor.isNull(POS_TELEFONE)) cursor.getString(POS_TELEFONE) else ""
                contato.celular = if(!cursor.isNull(POS_CELULAR)) cursor.getString(POS_CELULAR) else ""
                contato.conjuge = if(!cursor.isNull(POS_CONJUGE)) cursor.getString(POS_CONJUGE) else ""
                contato.tipo = cursor.getString(POS_TIPO)
                contato.time = cursor.getString(POS_TIME)
                contato.email = cursor.getString(POS_EMAIL)
                contato.dataNascimento = if(!cursor.isNull(POS_DATA_NASCIMENTO)){
                    val dateString = cursor.getString(POS_DATA_NASCIMENTO)
                    DateUtil.parse(dateString,Constantes.DATETIME_SQLITE)
                } else null
                contato.dataNascimentoConjuge = if(!cursor.isNull(POS_DATA_NASCIMENTO_CONJUGE)){
                    val dateString = cursor.getString(POS_DATA_NASCIMENTO_CONJUGE)
                    DateUtil.parse(dateString,Constantes.DATETIME_SQLITE)
                } else null
                list.add(contato)
            }
            return list
        }
    }
}