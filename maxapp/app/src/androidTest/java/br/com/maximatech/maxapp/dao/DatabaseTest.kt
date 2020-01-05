package br.com.maximatech.maxapp.dao

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.maximatech.maxapp.database.DatabaseHelper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var context: Context

    @Before
    fun setup(){
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun testNewDatabse(){
        val db = DatabaseHelper(context).readableDatabase
        assertNotNull(db)
    }
}