package br.com.maximatech.maxapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class ClienteHolder(
    val cliente: Cliente
):Serializable

 class Cliente:Serializable{
    var id:Int = 0
     var localId = 0L
    var codigo:String =""
    @SerializedName("razao_social")
    var razaoSocial:String = ""
    var nomeFantasia:String = ""
    var cnpj:String = ""
    var cpf:String = ""
    @SerializedName("ramo_atividade")
    var ramoAtividade:String = ""
    var endereco:String = ""
    var status:String=""
    var contatos:List<ContatoCliente>? = null
}

 class ContatoCliente:Serializable{
     var id = 0
     var nome:String=""
     var telefone:String = ""
     var celular:String = ""
     var conjuge:String = ""
     var tipo:String = ""
     var time:String = ""
     @SerializedName("e_mail")
     var email:String = ""
     @SerializedName("data_nascimento")
     var dataNascimento:Date? = null
     var dataNascimentoConjuge:Date? = null
 }