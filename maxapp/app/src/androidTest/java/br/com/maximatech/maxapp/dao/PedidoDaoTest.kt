package br.com.maximatech.maxapp.dao

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.maximatech.maxapp.dao.definitions.IPedidoDao
import br.com.maximatech.maxapp.database.DatabaseHelper
import br.com.maximatech.maxapp.service.PedidoService
import br.com.maximatech.maxapp.service.definitions.IPedidoService
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class PedidoDaoTest {
    private lateinit var context:Context
    private lateinit var pedidoService:IPedidoService
    private lateinit var pedidoDao:IPedidoDao
    private lateinit var databaseHelper:DatabaseHelper

    @Before
    fun setup(){
        context = InstrumentationRegistry.getInstrumentation().targetContext
        pedidoService = PedidoService()
        pedidoDao = PedidoDao()
        databaseHelper = DatabaseHelper(context)
    }

    @Test
    fun testPersistPedidos(){
        val pedidos = pedidoService.listPedidos()
        pedidoDao.persist(databaseHelper.writableDatabase,pedidos)
    }

    @Test
    fun testListPedidos(){
        testPersistPedidos()
        val pedidos = pedidoDao.list(databaseHelper.readableDatabase)
        assertNotNull(pedidos)
        assertNotEquals(0,pedidos.size)
    }

    @Test
    fun testClear(){
        pedidoDao.clear(databaseHelper.writableDatabase)
        val pedidos = pedidoDao.list(databaseHelper.readableDatabase)
        assertEquals(0,pedidos.size)
    }

}