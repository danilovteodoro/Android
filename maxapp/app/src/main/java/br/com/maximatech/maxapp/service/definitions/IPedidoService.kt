package br.com.maximatech.maxapp.service.definitions

import br.com.maximatech.maxapp.model.Pedido

interface IPedidoService {
    fun listPedidos():List<Pedido>
}