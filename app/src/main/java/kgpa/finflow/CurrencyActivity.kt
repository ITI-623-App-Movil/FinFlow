package kgpa.finflow

import Controller.CurrencyController
import Entity.Currency
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CurrencyActivity : AppCompatActivity() {
    lateinit var txtID: EditText
    lateinit var txtName: EditText
    lateinit var txtCode: EditText
    private lateinit var menuItemDelete: MenuItem

    private lateinit var currencyController: CurrencyController

    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_currency)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.TableLayout_currency)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        currencyController = CurrencyController(this)

        txtID = findViewById(R.id.txtID_currency)
        txtName = findViewById(R.id.txtName_currency)
        txtCode = findViewById(R.id.txtCode_currency)

        val btnSearch = findViewById<ImageButton>(R.id.btnSearchId_currency)
        btnSearch.setOnClickListener(View.OnClickListener {
            searchCurrency(txtID.text.toString().trim().toInt())
        })
    }

    // #region Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_crud, menu)
        menuItemDelete = menu!!.findItem(R.id.menu_delete)
        menuItemDelete.isVisible = isEditMode
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_save -> {
                if (isEditMode) {
                    Util.Util.showDialogCondition(this
                        , getString(R.string.TextSaveActionQuestion)
                        , { saveCurrency() })
                } else {
                    saveCurrency()
                }
                return true
            }
            R.id.menu_delete ->{
                Util.Util.showDialogCondition(this
                    , getString(R.string.TextDeleteActionQuestion)
                    , { deleteCurrency() })
                return true
            }
            R.id.menu_cancel -> {
                cleanScreen()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // #endregion

    // #region CRUD
    private fun searchCurrency(id: Int){
        try {
            val currency = currencyController.getById(id)
            isEditMode = true
            txtID.setText(currency?.ID.toString())
            txtID.isEnabled = false
            txtName.setText(currency?.Name)
            txtCode.setText(currency?.Code)

            invalidateOptionsMenu()
        } catch (e: Exception){
            cleanScreen()
            Toast.makeText(this, e.message.toString(),
                Toast.LENGTH_LONG).show()
        }
    }

    fun saveCurrency() {
        try {
            if (isValidationData()){
                if (currencyController.getById(txtID.text.toString().trim().toInt()) != null
                    && !isEditMode){
                    Toast.makeText(this, getString(R.string.MsgDuplicateDate)
                        , Toast.LENGTH_LONG).show()
                } else {
                    val currency = Currency()
                    currency.ID = txtID.text.toString().trim().toInt()
                    currency.Name = txtName.text.toString()
                    currency.Code = txtCode.text.toString()

                    if (!isEditMode)
                        currencyController.addCurrency(currency)
                    else
                        currencyController.updateCurrency(currency)

                    cleanScreen()

                    Toast.makeText(this, getString(R.string.MsgSaveSuccess)
                        , Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Datos incompletos"
                    , Toast.LENGTH_LONG).show()
            }
        }catch (e: Exception){
            Toast.makeText(this, e.message.toString()
                , Toast.LENGTH_LONG).show()
        }
    }

    fun deleteCurrency(): Unit{
        try {
            currencyController.removeCurrency(txtID.text.toString().trim().toInt())
            cleanScreen()
            Toast.makeText(this, getString(R.string.MsgDeleteSuccess)
                , Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Toast.makeText(this, e.message.toString()
                , Toast.LENGTH_LONG).show()
        }
    }
    // #endregion

    // #region Utilities
    private fun cleanScreen(){
        isEditMode = false
        txtID.isEnabled = true
        txtID.setText("")
        txtName.setText("")
        txtCode.setText("")
        invalidateOptionsMenu()
    }

    fun isValidationData(): Boolean {
        return txtID.text.trim().isNotBlank() && txtName.text.trim().isNotBlank()
                && txtCode.text.trim().isNotBlank() && txtCode.text.trim().length == 3
    }
    // #endregion
}