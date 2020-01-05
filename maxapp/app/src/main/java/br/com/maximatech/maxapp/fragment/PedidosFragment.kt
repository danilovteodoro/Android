package br.com.maximatech.maxapp.fragment
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.maximatech.maxapp.R
import br.com.maximatech.maxapp.adapter.PedidoAdapter
import br.com.maximatech.maxapp.dao.PedidoDao
import br.com.maximatech.maxapp.dao.definitions.IPedidoDao
import br.com.maximatech.maxapp.database.DatabaseHelper
import br.com.maximatech.maxapp.extensions.hideProgress
import br.com.maximatech.maxapp.extensions.showProgress
import br.com.maximatech.maxapp.model.Pedido
import br.com.maximatech.maxapp.model.PedidoHolder
import br.com.maximatech.maxapp.service.PedidoService
import br.com.maximatech.maxapp.service.definitions.IPedidoService
import kotlinx.android.synthetic.main.fragment_pedidos.*
import kotlinx.android.synthetic.main.fragment_pedidos.view.*
import util.NetworkUtil
import util.Task

class PedidosFragment : Fragment() {
    private lateinit var pedidoService:IPedidoService
    private lateinit var pedidoDao:IPedidoDao
    private var pedidos:MutableList<Pedido> = mutableListOf()
    private  lateinit var adapter:PedidoAdapter

    companion object {
        private const val SAVED_PEDIDO = "savedPedido"
        private const val TAG = "pedidoFragment"
        fun getInstance() : PedidosFragment{
            val fragment  = PedidosFragment()
            fragment.setHasOptionsMenu(true)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pedidos,container,false)
        view.rcPedidos.layoutManager = LinearLayoutManager(context)
        adapter = PedidoAdapter(context!!,pedidos)
        view.rcPedidos.adapter = adapter
        pedidoService = PedidoService()
        pedidoDao = PedidoDao()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.setTitle(R.string.str_historico)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_PEDIDO)){
            val pedidoHolder = savedInstanceState.getSerializable(SAVED_PEDIDO) as PedidoHolder
            pedidos.addAll(pedidoHolder.pedidos)
            adapter.notifyDataSetChanged()
        }
        if(pedidos.isEmpty()){
            if(NetworkUtil.isNetworkConnected(context!!)){
                getPedidos()
            }else{
                getPedidosFromDb()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val pedidoHolder = PedidoHolder(pedidos)
        outState.putSerializable(SAVED_PEDIDO,pedidoHolder)
    }

    private fun getPedidos() {
        val task = Task<List<Pedido>>(
            {pedidos->
                this.pedidos.addAll(pedidos)
                if(isAdded){
                    hideProgress(rcPedidos)
                    adapter.notifyDataSetChanged()
                }
                Handler().post {
                    savePedidos()
                }
            },
            {e->
                Log.e(TAG,e.message,e)
            }
        )
        showProgress(rcPedidos)
        task.execute({
            pedidoService.listPedidos()
        })
    }

    fun savePedidos(){
        if(!pedidos.isNullOrEmpty()&&isAdded){
            val db = DatabaseHelper(context!!).writableDatabase
            try{
               pedidoDao.clear(db)
               pedidoDao.persist(db,pedidos)
               db.close()
           }catch (e:Exception){
                Log.e(TAG,e.message,e)
            }finally {
                db.close()
            }
        }
    }

    fun getPedidosFromDb(){
        val db = DatabaseHelper(context!!).readableDatabase
        val task = Task<List<Pedido>>(
            {pedidos->
                db.close()
                this.pedidos.addAll(pedidos)
                if(isAdded){
                    hideProgress(rcPedidos)
                    adapter.notifyDataSetChanged()
                }
            },
            {e->
                db.close()
                Log.e(TAG,e.message,e)
            }
        )
        showProgress(rcPedidos)
        task.execute({
            pedidoDao.list(db)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_historico_pedidos,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.legendas_menu){
            LegendasDialogFragment.show(fragmentManager!!)
        }
        return super.onOptionsItemSelected(item)
    }


}