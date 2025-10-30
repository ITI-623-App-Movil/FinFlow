package Controller

import Data.IDataManager
import Data.MemoryDataManager
import Entity.Currency
import android.content.Context
import kgpa.finflow.R

class CurrencyController {
    private var dataManager: IDataManager = MemoryDataManager
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun addCurrency(currency: Currency) {
        try {
            dataManager.addCurrency(currency)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    fun updateCurrency(currency: Currency) {
        try {
            dataManager.updateCurrency(currency)
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    fun getCurrency(): List<Currency> {
        try {
            return dataManager.getAllCurrency()
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }
    }

    fun getById(id: Int) : Currency {
        try {
            val result = dataManager.getByIdCurrency(id)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun getByName(name: String) : Currency {
        try {
            val result = dataManager.getByNameCurrency(name)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    fun getByCode(code: String) : Currency {
        try {
            val result = dataManager.getByCodeCurrency(code)
            if (result == null) {
                throw Exception(context.getString(R.string.ErrorMsgGetById))
            }

            return result
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }
}