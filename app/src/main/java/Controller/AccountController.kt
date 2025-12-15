package Controller

import Data.IDataManager
import Data.MemoryDataManager
import Entity.Account
import Entity.AccountDTO
import Entity.Category
import Entity.Currency
import Services.APIService
import android.content.Context
import kgpa.finflow.R

class AccountController {
    private var context: Context

    constructor(context: Context) {
        this.context = context
    }

    suspend fun addAccount(account: Account) {
        try {
            APIService.apiAccount.addAccount(getAccountDTO(account))
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgAdd))
        }
    }

    suspend fun updateAccount(account: Account) {
        try {
            APIService.apiAccount.updateAccount(account.ID, getAccountDTO(account))
        } catch (e: Exception){
            throw Exception(context.getString(R.string.ErrorMsgUpdate))
        }
    }

    suspend fun getAccount(): List<Account> {
        var accounts = mutableListOf<Account>()

        try {
            val response = APIService.apiAccount.getAccount()

            response.forEach { item ->
                accounts.add(getAccountObject(item))
            }
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetAll))
        }

        return accounts
    }

    suspend fun getById(id: Int) : Account? {
        try {
            val response = APIService.apiAccount.getById(id)
            return getAccountObject(response)
        } catch (e: Exception) {
            throw Exception(context.getString(R.string.ErrorMsgGetById))
        }
    }

    private fun getAccountDTO(account: Account): AccountDTO {
        var accountDTO = AccountDTO(account.ID, account.Name, account.Balance, account.Currency,
            account.AccountType)
        return accountDTO
    }

    private fun getAccountObject(item: AccountDTO): Account {
        var account = Account()
        account.ID = item.ID
        account.Name = item.Name
        account.Balance = item.Balance
        account.Currency = Currency(item.Currency.ID, item.Currency.Code, item.Currency.Name)
        account.AccountType = item.AccountType

        return account
    }
}