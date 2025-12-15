package kgpa.finflow

import Controller.TransactionController
import Entity.Transaction
import Interfaces.OnItemClickListener
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch

class TransactionListActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var transactionController: TransactionController
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: TransactionAdapter
    private lateinit var mycontext: Context

    object SessionManager {
        var transaction: Transaction? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transaction_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mycontext = this
        transactionController = TransactionController(this)
        recycler = findViewById(R.id.rvtransactiion)
        recycler.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(
            recycler.context,
            DividerItemDecoration.VERTICAL
        )
        recycler.addItemDecoration(divider)

        getTransaction()
    }

    private fun getTransaction() {
        val context = this
        try {
            lifecycleScope.launch {
                val transactions = transactionController.getTransaction()

                adapter = TransactionAdapter(
                    transactions,
                    context
                )
                recycler.adapter = adapter
            }
        } catch (e: Exception) {
            Toast.makeText(
                mycontext, e.message.toString(), Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onItemClicked(transaction: Transaction) {
        SessionManager.transaction = transaction
        Util.Util.openActivity(this, TransactionActivity::class.java)
    }
}