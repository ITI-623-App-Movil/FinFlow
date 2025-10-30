package Controller

import Data.IDataManager
import Data.MemoryDataManager
import Entity.Transaction
import android.content.Context
import kgpa.finflow.R

class TransactionController {
    private var dataManager: IDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addTransaction(transaction: Transaction) {
        try {
            dataManager.addTransaction(transaction)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateTransaction(transaction: Transaction) {
        try {
            dataManager.updateTransaction(transaction)
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getTransaction(): List<Transaction> {
        try {
            return dataManager.getAllTransaction()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getById(id: Int) : Transaction {
        try {
            val result = dataManager.getByIdTransaction(id)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun getByName(name: String) : Transaction {
        try {
            val result = dataManager.getByNameTransaction(name)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }
}