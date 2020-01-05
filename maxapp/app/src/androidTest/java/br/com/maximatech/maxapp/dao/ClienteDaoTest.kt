package br.com.maximatech.maxapp.dao

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.maximatech.maxapp.dao.definitions.IClienteDao
import br.com.maximatech.maxapp.database.DatabaseHelper
import br.com.maximatech.maxapp.service.ClienteService
import br.com.maximatech.maxapp.service.definitions.IClienteService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ClienteDaoTest {
    private lateinit var context: Context
    private lateinit var clienteService:IClienteService
    private lateinit var clienteDao:IClienteDao
    private lateinit var databaseHelper: DatabaseHelper

    @Before
    fun setup(){
        context = InstrumentationRegistry.getInstrumentation().targetContext
        clienteService = ClienteService()
        clienteDao = ClienteDao()
        databaseHelper = DatabaseHelper(context)
    }

    @Test
    fun testPersistCliente(){
        val cliente = clienteService.getCliente()
        val db = databaseHelper.writableDatabase
        clienteDao.persist(db,cliente)
//        db.close()db.close()
    }
    @Test
    fun testDeleteCliente(){
        val db =databaseHelper.writableDatabase
        clienteDao.clear(db)
        db.close()
    }

    @Test
    fun testGet(){
        testPersistCliente()
        val db = databaseHelper.readableDatabase
        val cliente = clienteDao.get(db)
        assertNotNull(cliente)
        assertNotEquals(0,cliente!!.contatos!!.size)
        db.close()
    }
}