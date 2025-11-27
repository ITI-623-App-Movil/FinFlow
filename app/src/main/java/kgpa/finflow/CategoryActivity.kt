package kgpa.finflow

import Controller.CategoryController
import Entity.Category
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CategoryActivity : AppCompatActivity() {
    lateinit var txtID: EditText
    lateinit var txtName: EditText
    lateinit var spType: Spinner
    private lateinit var menuItemDelete: MenuItem

    private lateinit var categoryController: CategoryController

    private var isEditMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_category)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.TableLayout_category)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        categoryController = CategoryController(this)

        txtID = findViewById(R.id.txtID_category)
        txtName = findViewById(R.id.txtName_category)
        spType = findViewById(R.id.spType_category)

        val types = listOf("Income", "Expense")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            types
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spType.adapter = adapter

        val btnSearch = findViewById<ImageButton>(R.id.btnSearchId_category)
        btnSearch.setOnClickListener(View.OnClickListener {
            searchCategory(txtID.text.toString().trim().toInt())
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
                        , { saveCategory() })
                } else {
                    saveCategory()
                }
                return true
            }
            R.id.menu_delete ->{
                Util.Util.showDialogCondition(this
                    , getString(R.string.TextDeleteActionQuestion)
                    , { deleteCategory() })
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
    private fun searchCategory(id: Int){
        try {
            val category = categoryController.getById(id)
            isEditMode = true
            txtID.setText(category?.ID.toString())
            txtID.isEnabled = false
            txtName.setText(category?.Name)
            val position = (spType.adapter as ArrayAdapter<String>)
                .getPosition(category?.Type)
            spType.setSelection(position)

            invalidateOptionsMenu()
        } catch (e: Exception){
            cleanScreen()
            Toast.makeText(this, e.message.toString(),
                Toast.LENGTH_LONG).show()
        }
    }

    fun saveCategory() {
        try {
            if (isValidationData()){
                if (categoryController.getById(txtID.text.toString().trim().toInt()) != null
                    && !isEditMode){
                    Toast.makeText(this, getString(R.string.MsgDuplicateDate)
                        , Toast.LENGTH_LONG).show()
                } else {
                    val category = Category()
                    category.ID = txtID.text.toString().trim().toInt()
                    category.Name = txtName.text.toString()
                    category.Type = spType.selectedItem.toString()

                    if (!isEditMode)
                        categoryController.addCategory(category)
                    else
                        categoryController.updateCategory(category)

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

    fun deleteCategory(): Unit{
        try {
            categoryController.removeCategory(txtID.text.toString().trim().toInt())
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
        spType.setSelection(0)
        invalidateOptionsMenu()
    }

    fun isValidationData(): Boolean {
        return txtID.text.trim().isNotBlank() && txtName.text.trim().isNotBlank()
                && spType.selectedItem != null
    }
    // #endregion
}