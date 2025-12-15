package kgpa.finflow

import Controller.AccountController
import Controller.CategoryController
import Controller.TransactionController
import Entity.Account
import Entity.Category
import Entity.Transaction
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar
import kotlin.collections.mutableListOf

class TransactionActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    lateinit var txtID: EditText
    lateinit var txtAmount: EditText
    lateinit var txtDescription: EditText
    lateinit var lblDate: TextView
    lateinit var spinnerCategories: Spinner
    lateinit var spinnerAccounts: Spinner

    private lateinit var transactionController: TransactionController
    private lateinit var categoryController: CategoryController
    private lateinit var accountController: AccountController

    private var _categoryList = mutableListOf<Category>()
    private var _accountList = mutableListOf<Account>()
    private var transactionEdit: Transaction? = null

    private var isEditMode: Boolean = false
    private var day: Int = 0
    private var month: Int = 0
    private var year: Int = 0

    private lateinit var menuItemDelete: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transaction)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.TableLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        transactionController = TransactionController(this)
        categoryController = CategoryController(this)
        accountController = AccountController(this)

        txtID = findViewById(R.id.txtID_transaction)
        txtAmount = findViewById(R.id.txtAmount_transaction)
        txtDescription = findViewById(R.id.txtDescription_transaction)
        lblDate = findViewById(R.id.lblDate_transaction)

        spinnerCategories = findViewById(R.id.spinner_cateogries)
        spinnerAccounts = findViewById(R.id.spinner_accounts)

        transactionEdit = TransactionListActivity.SessionManager.transaction

        isEditMode = transactionEdit != null

        if (isEditMode) {
            loadTransaction()
        }

        resetDate()

        lifecycleScope.launch {
            loadCategories()
            loadAccounts()
        }

        val btnSelectedDate = findViewById<ImageButton>(R.id.btnSelecteddate_transaction)
        btnSelectedDate.setOnClickListener(View.OnClickListener { view ->
            showDatePickerDialog()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_crud, menu)
        menuItemDelete= menu!!.findItem(R.id.menu_delete)
        menuItemDelete.isVisible = isEditMode
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_save ->{
                if (isEditMode){
                    Util.Util.showDialogCondition(this
                        , getString(R.string.TextSaveActionQuestion)
                        , {
                            lifecycleScope.launch { saveTransaction() } })
                } else {
                    lifecycleScope.launch { saveTransaction() }
                }
                return true
            }
            R.id.menu_delete ->{
                Util.Util.showDialogCondition(this
                    , getString(R.string.TextDeleteActionQuestion)
                    , { deleteTransaction() })
                return true
            }
            R.id.menu_cancel ->{
                cleanScreen()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getDateFormatString(dayOfMonth: Int, month: Int, year: Int): String {
        return "${String.format("%02d", dayOfMonth)}/${String.format("%02d",month)}/${year}"
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        lblDate.text = getDateFormatString(day, month + 1, year)

        this.year = year
        this.month = month
        this.day = day
    }

    private suspend fun saveTransaction(){
        try {
            if (isValidationData()){
                val transaction = Transaction()

                transaction.ID = txtID.text.toString().toInt()
                transaction.Amount = txtAmount.text.toString().toDouble()
                transaction.Description = txtDescription.text.toString()
                val bDateParse = Util.Util.parseStringToDateModern(
                    lblDate.text.toString(), "dd/MM/yyyy")
                transaction.Date = LocalDate.of(bDateParse?.year!!,
                    bDateParse.month.value, bDateParse.dayOfMonth
                )

                transaction.Category = spinnerCategories.selectedItem as Category
                transaction.Account = spinnerAccounts.selectedItem as Account

                if (!isEditMode) {
                    transactionController.addTransaction(transaction)
                    cleanScreen()
                }
                else {
                    transactionController.updateTransaction(transaction)
                }

                Toast.makeText(this, getString(R.string.MsgSaveSuccess)
                    , Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Datos incompletos"
                    , Toast.LENGTH_LONG).show()
            }
        }catch (e: Exception){
            Toast.makeText(this, e.message.toString()
                , Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteTransaction(): Unit{
        try {
            lifecycleScope.launch {
                transactionController.removeTransaction(txtID.text.toString().toInt())
            }
            cleanScreen()
            Toast.makeText(this, getString(R.string.MsgDeleteSuccess)
                , Toast.LENGTH_LONG).show()
        }catch (e: Exception){
            Toast.makeText(this, e.message.toString()
                , Toast.LENGTH_LONG).show()
        }
    }

    private fun loadTransaction() {
        txtID.isEnabled = false
        txtID.setText(transactionEdit!!.ID.toString())
        txtAmount.setText(transactionEdit!!.Amount.toString())
        txtDescription.setText(transactionEdit!!.Description)
        lblDate.text = Util.Util.parseDateToString(transactionEdit!!.Date,
            "dd/MM/yyyy")
        spinnerCategories.setSelection(
            _categoryList.indexOfFirst { it.ID == transactionEdit!!.Category.ID }
        )
        spinnerAccounts.setSelection(
            _accountList.indexOfFirst { it.ID == transactionEdit!!.Account.ID }
        )
    }

    private suspend fun loadCategories() {
        try {
            val categoryList = categoryController.getCategory()

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                categoryList
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCategories.adapter = adapter

            _categoryList = categoryList as MutableList<Category>
        } catch (e: Exception) {
            Toast.makeText(
                this, e.message.toString(), Toast.LENGTH_LONG
            ).show()
        }
    }

    private suspend fun loadAccounts() {
        try {
            val accountList = accountController.getAccount()

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                accountList
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerAccounts.adapter = adapter

            _accountList = accountList as MutableList<Account>
        } catch (e: Exception){
            Toast.makeText(this, e.message.toString()
                , Toast.LENGTH_LONG).show()
        }
    }

    fun isValidationData(): Boolean {
        return txtID.text.trim().isNotBlank() && txtDescription.text.trim().isNotBlank()
    }

    private fun showDatePickerDialog(){
        val datePickerDialog = DatePickerDialog(this, this
            , year, month, day)
        datePickerDialog.show()
    }

    private fun cleanScreen(){
        resetDate()
        isEditMode = false
        txtID.isEnabled = true
        txtID.setText("")
        txtAmount.setText("")
        txtDescription.setText("")
        lblDate.text = ""
        spinnerCategories.setSelection(0)
        spinnerAccounts.setSelection(0)

        invalidateOptionsMenu()
    }

    private fun resetDate(){
        val calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
    }
}