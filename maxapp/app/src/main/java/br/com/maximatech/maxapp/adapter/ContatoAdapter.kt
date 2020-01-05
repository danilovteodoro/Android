package br.com.maximatech.maxapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.maximatech.maxapp.R
import br.com.maximatech.maxapp.model.ContatoCliente
import kotlinx.android.synthetic.main.inflater_contatos.view.*
import util.DateUtil

class ContatoAdapter(val context:Context, var contatos:List<ContatoCliente>):RecyclerView.Adapter<ContatoAdapter.ViewHolder>() {
    val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.inflater_contatos,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contatos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contato = contatos[position]
        holder.txtNomeContato.text = contato.nome
        holder.txtTelefone.text = contato.telefone
        holder.txtCelular.text = contato.celular
        holder.txtEmail.text = contato.email
        if(contato.dataNascimento != null){
            holder.txtDataNasc.text = DateUtil.format(contato.dataNascimento!!,"dd/MM/yyyy")
        }
        holder.txtConjuge.text = if(contato.conjuge.isNotEmpty()){
            contato.conjuge
        }else{
            context.getString(R.string.str_NaoInformado)
        }
        holder.txtDtNascConjuge.text = if(contato.dataNascimentoConjuge != null){
            DateUtil.format(contato.dataNascimentoConjuge!!,"dd/MM/yyyy")
        }else{
            context.getString(R.string.str_NaoInformado)
        }
        holder.txtTipo.text = contato.tipo
        holder.txtTime.text = contato.time
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val txtNomeContato = view.txtNomeContato
        val txtTelefone = view.txtTelefone
        val txtEmail = view.txtemail
        val txtCelular = view.txtCelular
        val txtDataNasc = view.txtDataNasc
        val txtConjuge = view.txtConjuge
        val txtDtNascConjuge = view.txtDtNascConjuge
        val txtTipo = view.txtTipo
        val txtTime = view.txtTime
    }
}