package br.com.maximatech.maxapp.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.maximatech.maxapp.R
import br.com.maximatech.maxapp.adapter.ContatoAdapter
import br.com.maximatech.maxapp.dao.ClienteDao
import br.com.maximatech.maxapp.dao.definitions.IClienteDao
import br.com.maximatech.maxapp.database.DatabaseHelper
import br.com.maximatech.maxapp.extensions.hideProgress
import br.com.maximatech.maxapp.extensions.showProgress
import br.com.maximatech.maxapp.model.Cliente
import br.com.maximatech.maxapp.service.ClienteService
import br.com.maximatech.maxapp.service.definitions.IClienteService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_cliente.*
import kotlinx.android.synthetic.main.fragment_cliente.view.*
import util.Constantes
import util.DateUtil
import util.NetworkUtil
import util.Task
import java.util.*


class ClienteFragment : Fragment(){
    private val TAG = "dadosClienteFrag"
    private lateinit var clienteService: IClienteService
    private lateinit var clienteDao:IClienteDao
    private var cliente: Cliente? = null

    companion object {
        private val SAVED_CLIENTE = "savedCliente"
        fun getInstance():ClienteFragment{
            return ClienteFragment()
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cliente,container,false)
        view.rcContatos.layoutManager = LinearLayoutManager(context)
        clienteService = ClienteService()
        clienteDao = ClienteDao()
        view.btnVerificaStatusCli.setOnClickListener {
            showSnackbar(view.coordinator)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.setTitle(R.string.str_dadosCliente)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVED_CLIENTE,cliente)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cliente = if(savedInstanceState != null && savedInstanceState!!.containsKey(SAVED_CLIENTE)){
            savedInstanceState!!.getSerializable(SAVED_CLIENTE) as Cliente
        } else null
        if(cliente == null){
           if(NetworkUtil.isNetworkConnected(context!!)){
               getCliente()
           }else{
               getClienteFromDb()
           }
            return
        }
        fillClienteLayout()

    }

    fun getCliente(){
        val task = Task<Cliente>(
            {cliente->
                this.cliente = cliente
                if(isAdded){
                    hideProgress(mainView)
                    fillClienteLayout()
                }
                Handler().post {
                    saveCliente()
                }
            },
            {e->
                Log.e(TAG,e.message,e)
            }
        )
        showProgress(mainView)
        task.execute({
              clienteService.getCliente()
        })
    }

    fun saveCliente(){
        if(cliente!=null&&isAdded){
            val db = DatabaseHelper(context!!).writableDatabase
            clienteDao.clear(db)
            clienteDao.persist(db,cliente!!)
            db.close()
        }
    }

    fun getClienteFromDb(){
        val db = DatabaseHelper(context!!).writableDatabase
        val task = Task<Cliente?>(
            {cliente->
                db.close()
                this.cliente = cliente
                if(isAdded){
                    hideProgress(mainView)
                    fillClienteLayout()
                }
            },
            {e->
                Log.e(TAG,e.message,e)
            }
        )
        showProgress(mainView)
        task.execute({
            clienteDao.get(db)
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
            rcContatos.adapter = ContatoAdapter(context!!,cliente!!.contatos!!)
        }
    }

    fun showSnackbar(view:View){
        if(cliente != null){
            val text = DateUtil.format(Date(),Constantes.BR_DATE_PATTERN)
                .plus(" - Status ").plus(cliente!!.status)
            val snackBar = Snackbar.make(view,text,Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(resources.getColor(R.color.green))
            snackBar.setAction(R.string.str_fechar){
                snackBar.dismiss()
            }
            if(!snackBar.isShown)
                snackBar.show()

        }
    }

}