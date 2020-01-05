package br.com.maximatech.maxapp.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.ImageSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import br.com.maximatech.maxapp.R
import br.com.maximatech.maxapp.model.Pedido
import kotlinx.android.synthetic.main.inflater_pedidos.view.*
import util.DateUtil
import util.TextUtil
import java.lang.StringBuilder
import java.util.*

class PedidoAdapter(val context:Context,var pedidos:MutableList<Pedido>) : RecyclerView.Adapter<PedidoAdapter.ViewHolder>() {
    val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.inflater_pedidos,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pedidos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.txtPedido.text = pedido.toString()
        holder.txtCliente.text = pedido.getCliente()
        holder.txtStatus.text = pedido.status

        if(!pedido.critica.isNullOrEmpty()){
            holder.lblCritica.visibility = View.VISIBLE
            when(pedido.critica){
                "SUCESSO"-> holder.iconCritica.setBackgroundDrawable(context.resources.getDrawable(R.drawable.ic_maxima_critica_sucesso))
                "FALHA_PARCIAL"-> holder.iconCritica.setBackgroundDrawable(context.resources.getDrawable(R.drawable.ic_maxima_critica_alerta))
                "FALHA_TOTAL"-> {
                    holder.iconCritica.setBackgroundDrawable(context.resources.getDrawable(R.drawable.shape_red))
                    holder.iconCritica.text = "!"

                }


            }
        }

        if(pedido.data != null){
            val pattern = when {
                DateUtil.isToday(pedido.data!!) -> {
                    "HH:mm"
                }
                DateUtil.isSameYear(pedido.data!!) -> {
                    "dd MMM"
                }
                else -> {
                    "dd MMM yy"
                }
            }
            holder.txtHoraPedido.text = DateUtil.format(pedido.data!!,pattern)
        }

        val backgroundResource:Int = when{
            pedido.tipo == "ORCAMENTO" -> {
                holder.iconStatus.text = "O"
                R.drawable.shape_gray2
            }
            pedido.status.toUpperCase() == "EM PROCESSAMENTO" ->  R.drawable.shape_processando
            pedido.status == "Pendente" -> {
                holder.iconStatus.text = "P"
                R.drawable.shape_gray3
            }

            !pedido.critica.isNullOrEmpty() && pedido.critica == "FALHA_PARCIAL"->{
                holder.iconStatus.text = "!"
                R.drawable.shape_yellow
            }

            else -> {
                holder.iconStatus.text = "L"
                R.drawable.shape_blue2
            }
        }
        holder.iconStatus.setBackgroundResource(backgroundResource)


        if(pedido.legendas != null){
            for(legenda in pedido.legendas!!){
                when(legenda){
                    "PEDIDO_SOFREU_CORTE" -> holder.legendaCorte.visibility = View.VISIBLE
                    "PEDIDO_COM_FALTA" -> holder.legendaFalta.visibility = View.VISIBLE
                    "PEDIDO_CANCELADO_ERP" -> holder.legendaCancelado.visibility = View.VISIBLE
                    "PEDIDO_COM_DEVOLUCAO" -> holder.legendaDevolucao.visibility = View.VISIBLE
                    "PEDIDO_FEITO_TELEMARKETING" -> holder.legendaDevolucao.visibility = View.VISIBLE
                }
            }
        }
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val txtPedido = view.txtPedido
        val txtCliente = view.txtCliente
        val txtStatus = view.txtStatus
        val iconCritica  = view.iconCritica
        val lblCritica = view.lblCritica
        val txtHoraPedido = view.txtHoraPedido

        val legendaCorte = view.legendaCorte
        val legendaFalta = view.legendaFalta
        val legendaCancelado = view.legendaCancelado
        val legendaDevolucao = view.legendaDevolucao
        val legedaTelemarketing = view.legendaTelemarketing
        val iconStatus = view.iconStatus


    }





}