package Data

import Entity.*

interface IDataManager {
    //region Account
    fun addAccount(account: Account)
    fun updateAccount(account: Account)
    fun removeAccount(id: Int)
    fun getAllAccount(): List<Account>
    fun getByIdAccount(id: Int): Account?
    fun getByNameAccount(name: String): Account?
    //endregion

    //region Bank
    fun addBank(bank: Bank)
    fun updateBank(bank: Bank)
    fun removeBank(id: Int)
    fun getAllBank(): List<Bank>
    fun getByIdBank(id: Int): Bank?
    fun getByNameBank(name: String): Bank?
    //endregion

    //region Category
    fun addCategory(category: Category)
    fun updateCategory(category: Category)
    fun removeCategory(id: Int)
    fun getAllCategory(): List<Category>
    fun getByIdCategory(id: Int): Category?
    fun getByNameCategory(name: String): Category?
    //endregion

    //region Currency
    fun addCurrency(currency: Currency)
    fun updateCurrency(currency: Currency)
    fun removeCurrency(id: Int)
    fun getAllCurrency(): List<Currency>
    fun getByIdCurrency(id: Int): Currency?
    fun getByNameCurrency(name: String): Currency?
    fun getByCodeCurrency(code: String): Currency?
    //endregion

    //region Tag
    fun addTag(tag: Tag)
    fun updateTag(tag: Tag)
    fun removeTag(id: Int)
    fun getAllTag(): List<Tag>
    fun getByIdTag(id: Int): Tag?
    fun getByNameTag(name: String): Tag?
    //endregion

    //region Transaction
    fun addTransaction(transaction: Transaction)
    fun updateTransaction(transaction: Transaction)
    fun removeTransaction(id: Int)
    fun getAllTransaction(): List<Transaction>
    fun getByIdTransaction(id: Int): Transaction?
    fun getByNameTransaction(description: String): Transaction?
    //endregion
}