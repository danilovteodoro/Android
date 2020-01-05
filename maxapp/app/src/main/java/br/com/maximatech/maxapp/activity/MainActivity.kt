package br.com.maximatech.maxapp.activity

import android.app.Activity
import android.app.TaskStackBuilder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.maximatech.maxapp.R
import br.com.maximatech.maxapp.fragment.AlvarasFragment
import br.com.maximatech.maxapp.fragment.ClienteFragment
import br.com.maximatech.maxapp.fragment.PedidosFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.appbar.*

class MainActivity : AppCompatActivity() {
    private lateinit var clienteFragment:ClienteFragment
    private lateinit var pedidosFragment:PedidosFragment
    private lateinit var alvarasFragment:AlvarasFragment

    enum class SCREEN(val value:Int) {
        CLIENTE(1),
        HISTORICO_PEDIDO(2),
        AJUSTES(3)
    }

    companion object {
         const val IT_SCREEN = "itScreen"
        fun start(activity:Activity,screen: SCREEN){
            val it = Intent(activity,MainActivity::class.java)
            it.putExtra(IT_SCREEN,screen.value)
            activity.startActivity(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clienteFragment = ClienteFragment.getInstance()
        pedidosFragment = PedidosFragment.getInstance()
        alvarasFragment = AlvarasFragment.getInstance()
        setSupportActionBar(appbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        bottomNavigation.setOnNavigationItemSelectedListener {menuItem ->
            when(menuItem.itemId){
                R.id.dadosClientMenu -> addClientFragment()
                R.id.historicoMenu -> addPedidosFragment()
                R.id.alvaraMenu -> addAlvaraFragment()
            }
            true
        }
        when(intent.getIntExtra(IT_SCREEN,0)){
            SCREEN.CLIENTE.value -> {
                addClientFragment()
                bottomNavigation.selectedItemId = R.id.dadosClientMenu
            }
            SCREEN.HISTORICO_PEDIDO.value -> {
                addPedidosFragment()
                bottomNavigation.selectedItemId = R.id.historicoMenu
            }
            SCREEN.AJUSTES.value -> {
                addAlvaraFragment()
                bottomNavigation.selectedItemId = R.id.alvaraMenu
            } else -> {
            addPedidosFragment()
            bottomNavigation.selectedItemId = R.id.historicoMenu
        }
        }
    }


    override fun onBackPressed() {
        val intent = Intent(this,DashboardActivity::class.java)
        intent.putExtra("exit", true)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent)
        super.onBackPressed()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    fun addClientFragment(){
        val tx = supportFragmentManager.beginTransaction()
        tx.replace(R.id.frameLayout,clienteFragment)
        tx.commit()

    }

    fun addPedidosFragment(){
        val tx = supportFragmentManager.beginTransaction()
        tx.replace(R.id.frameLayout,pedidosFragment)
        tx.commit()

    }
    fun addAlvaraFragment(){
        val tx = supportFragmentManager.beginTransaction()
        tx.replace(R.id.frameLayout,alvarasFragment)
        tx.commit()

    }
}
