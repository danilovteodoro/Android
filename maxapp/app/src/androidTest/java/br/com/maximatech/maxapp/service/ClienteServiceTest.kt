package br.com.maximatech.maxapp.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.maximatech.maxapp.service.ClienteService
import br.com.maximatech.maxapp.service.definitions.IClienteService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ClienteServiceTest{
    lateinit var clientService: IClienteService

    @Before
    fun setup(){
        clientService = ClienteService()
    }

    @Test
    fun testGetCliente(){
        val cliente = clientService.getCliente()
        assertNotNull(cliente)
        assertNotNull(cliente.contatos)
        assertNotEquals(0,cliente.contatos!!.size)
        assertEquals(30987,cliente.id)
    }

}