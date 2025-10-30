package Controller

import Data.IDataManager
import Data.MemoryDataManager
import Entity.Account
import android.content.Context
import kgpa.finflow.R

class AccountController {
    private var dataManager: IDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addAccount(account: Account) {
        try {
            dataManager.addAccount(account)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateAccount(account: Account) {
        try {
            dataManager.updateAccount(account)
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getAccount(): List<Account> {
        try {
            return dataManager.getAllAccount()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getById(id: Int) : Account {
        try {
            val result = dataManager.getByIdAccount(id)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun getByName(name: String) : Account {
        try {
            val result = dataManager.getByNameAccount(name)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }
}