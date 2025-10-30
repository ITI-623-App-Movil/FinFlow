package Controller

import Data.IDataManager
import Data.MemoryDataManager
import Entity.Bank
import android.content.Context
import kgpa.finflow.R

class BankController {
    private var dataManager: IDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addBank(bank: Bank) {
        try {
            dataManager.addBank(bank)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateBank(bank: Bank) {
        try {
            dataManager.updateBank(bank)
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getBank(): List<Bank> {
        try {
            return dataManager.getAllBank()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getById(id: Int) : Bank {
        try {
            val result = dataManager.getByIdBank(id)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun getByName(name: String) : Bank {
        try {
            val result = dataManager.getByNameBank(name)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }
}