package br.com.maximatech.maxapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.maximatech.maxapp.R
import br.com.maximatech.maxapp.adapter.ContatoAdapter
import br.com.maximatech.maxapp.model.Cliente
import br.com.maximatech.maxapp.service.ClienteService
import br.com.maximatech.maxapp.service.definitions.IClienteService
import kotlinx.android.synthetic.main.activity_dados_cliente.*
import kotlinx.android.synthetic.main.appbar.*
import util.Task

class DadosClienteActivity : AppCompatActivity() {
    private val TAG = "dadosClienteAct"
    private lateinit var clienteService:IClienteService
    private var cliente:Cliente? = null


    companion object {
        fun start(activity:Activity){
            val it = Intent(activity,DadosClienteActivity::class.java)
            activity.startActivity(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_cliente)
        setSupportActionBar(appbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        clienteService = ClienteService()
        rcContatos.layoutManager = LinearLayoutManager(this)
        getCliente()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    fun getCliente(){
        val task = Task<Cliente>(
            {cliente->
                this.cliente = cliente
                fillClienteLayout()
            },
            {e->
                Log.e(TAG,e.message,e)
            }
        )
        task.execute({
            clienteService.getCliente()
        })
    }

    fun fillClienteLayout(){
        if(cliente != null){
            txtRazaoSocial.text = "${cliente!!.id} - ${cliente!!.razaoSocial}"
            txtNomeFantazia.text = cliente!!.razaoSocial
            if(cliente!!.cpf.isNullOrEmpty()){
                txtCpf.visibility = View.GONE
                lblCpf.visibility = View.GONE
            }else{
                txtCpf.text = cliente!!.cpf
            }
            txtCnpj.text = cliente!!.cnpj
            txtRamoAtividade.text = cliente!!.ramoAtividade
            txtEndereco.text = cliente!!.endereco
            rcContatos.adapter = ContatoAdapter(this,cliente!!.contatos!!)
        }
    }

}