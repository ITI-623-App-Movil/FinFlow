package Controller

import Data.IDataManager
import Data.MemoryDataManager
import Entity.Account
import Entity.AccountDTO
import Entity.Category
import Entity.CategoryDTO
import Entity.Currency
import Entity.Transaction
import Entity.TransactionDTO
import Services.APIService
import android.content.Context
import kgpa.finflow.R
import java.time.LocalDate

class TransactionController {
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    suspend fun addTransaction(transaction: Transaction) {
        try {
            APIService.apiTransaction.addTransaction(getTransactionDTO(transaction))
        } catch (e: Exception) {
            throw Exception("${context.getString(R.string.ErrorMsgAdd)} " +
                    "Message: ${e.message}")
        }
    }

    suspend fun updateTransaction(transaction: Transaction) {
        try {
            APIService.apiTransaction.updateTransaction(transaction.ID,
                getTransactionDTO(transaction))
        } catch (e: Exception){
            throw Exception("${context.getString(R.string.ErrorMsgUpdate)} - " +
                    "${e.message}")
        }
    }

    suspend fun removeTransaction(id: Int) {
        try {
            APIService.apiTransaction.deleteTransaction(id)
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgRemove))
        }
    }

    suspend fun getTransaction(): List<Transaction> {
        var transactions = mutableListOf<Transaction>()

        try {
            val response = APIService.apiTransaction.getTransaction()

            response.forEach { item ->
                transactions.add(getTransactionObject(item))
            }
        } catch (e: Exception) {
            throw Exception("${context.getString(R.string.ErrorMsgGetAll)} - " +
                    "${e.message}")
        }

        return transactions
    }

    suspend fun getById(id: Int) : Transaction? {
        try {
            val response = APIService.apiTransaction.getById(id)
            return getTransactionObject(response)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    private fun getTransactionDTO(transaction: Transaction): TransactionDTO {
        var transactionDTO = TransactionDTO(transaction.ID, transaction.Amount, transaction.Description,
            Util.Util.parseDateToString(transaction.Date, "yyyy-MM-dd"),
            CategoryDTO(transaction.Category.ID, transaction.Category.Type, transaction.Category.Name),
            AccountDTO(transaction.Account.ID, transaction.Account.Name, transaction.Account.Balance,
                Currency(transaction.Account.Currency.ID, transaction.Account.Currency.Code,
                    transaction.Account.Currency.Name),
                transaction.Account.AccountType))
        return transactionDTO
    }

    private fun getTransactionObject(item: TransactionDTO): Transaction {
        var transaction = Transaction()
        val currency = Currency(item.Account.Currency.ID, item.Account.Currency.Code,
            item.Account.Currency.Name)

        transaction.ID = item.ID
        transaction.Amount = item.Amount
        transaction.Description = item.Description
        transaction.Date = Util.Util.parseStringToDateModern(item.Date.substring(0, 10),
            "yyyy-MM-dd")!!
        transaction.Category = Category(item.Category.ID, item.Category.Type,
            item.Category.Name)
        transaction.Account = Account(item.Account.ID, item.Account.Name,
            item.Account.Balance, currency, item.Account.AccountType)

        return transaction
    }
}