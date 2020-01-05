package br.com.maximatech.maxapp.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.maximatech.maxapp.model.Pedido
import br.com.maximatech.maxapp.service.PedidoService
import br.com.maximatech.maxapp.service.definitions.IPedidoService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class PedidoServiceTest {
    private lateinit var pedidoService: IPedidoService

    @Before
    fun setup(){
        pedidoService = PedidoService()
    }

    @Test
    fun tetListPedidos(){
        val pedidos:List<Pedido> = pedidoService.listPedidos()
        assertNotNull(pedidos)
        assertNotEquals(0,pedidos.size)
    }
}