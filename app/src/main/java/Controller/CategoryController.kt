package Controller;

import Data.IDataManager
import Data.MemoryDataManager
import Entity.Category
import android.content.Context
import kgpa.finflow.R

class CategoryController {
    private var dataManager: IDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addCategory(category: Category) {
        try {
            dataManager.addCategory(category)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateCategory(category: Category) {
        try {
            dataManager.updateCategory(category)
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getCategory(): List<Category> {
        try {
            return dataManager.getAllCategory()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getById(id: Int) : Category {
        try {
            val result = dataManager.getByIdCategory(id)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun getByName(name: String) : Category {
        try {
            val result = dataManager.getByNameCategory(name)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }
}