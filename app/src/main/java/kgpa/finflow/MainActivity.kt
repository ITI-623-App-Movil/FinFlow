package kgpa.finflow

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnAccount = findViewById<Button>(R.id.btnAccount_main)
        btnAccount.setOnClickListener(View.OnClickListener { view ->
            Util.Util.openActivity(this, AccountActivity::class.java)
        })

        val btnBank = findViewById<Button>(R.id.btnBank_main)
        btnBank.setOnClickListener(View.OnClickListener { view ->
            Util.Util.openActivity(this, BankActivity::class.java)
        })

        val btnCategory = findViewById<Button>(R.id.btnCategory_main)
        btnCategory.setOnClickListener(View.OnClickListener { view ->
            Util.Util.openActivity(this, CategoryActivity::class.java)
        })

        val btnCurrency = findViewById<Button>(R.id.btnCurrency_main)
        btnCurrency.setOnClickListener(View.OnClickListener { view ->
            Util.Util.openActivity(this, CurrencyActivity::class.java)
        })

        val btnTransaction = findViewById<Button>(R.id.btnTransaction_main)
        btnTransaction.setOnClickListener(View.OnClickListener { view ->
            Util.Util.openActivity(this, TransactionActivity::class.java)
        })

        val btnListTransaction = findViewById<Button>(R.id.btnListTransaction_main)
        btnListTransaction.setOnClickListener(View.OnClickListener { view ->
            Util.Util.openActivity(this, TransactionListActivity::class.java)
        })
    }
}