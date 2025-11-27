package kgpa.finflow

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AccountActivity : AppCompatActivity() {
    lateinit var txtID: EditText
    lateinit var txtName: EditText
    lateinit var txtType: EditText
    lateinit var txtBank: EditText
    lateinit var txtCurrency: EditText
    lateinit var txtBalance: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_account)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.TableLayout_account)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtID = findViewById(R.id.txtID_account)
        txtName = findViewById(R.id.txtName_account)
        txtType = findViewById(R.id.txtType_account)
        txtBank = findViewById(R.id.txtBank_account)
        txtCurrency = findViewById(R.id.txtCurrency_account)
        txtBalance = findViewById(R.id.txtBalance_account)
    }
}