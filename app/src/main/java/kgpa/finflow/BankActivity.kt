package kgpa.finflow

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BankActivity : AppCompatActivity() {
    lateinit var txtID: EditText
    lateinit var txtName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bank)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.TableLayout_bank)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtID = findViewById(R.id.txtID_bank)
        txtName = findViewById(R.id.txtName_bank)
    }
}