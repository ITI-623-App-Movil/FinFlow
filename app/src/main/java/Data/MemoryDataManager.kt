package Data

import Entity.*

object MemoryDataManager : IDataManager {
    private var accountList = mutableListOf<Account>()
    private var bankList = mutableListOf<Bank>()
    private var categoryList = mutableListOf<Category>()
    private var currencyList = mutableListOf<Currency>()
    private var tagList = mutableListOf<Tag>()
    private var transactionList = mutableListOf<Transaction>()

    //region Account
    override fun addAccount(account: Account) {
        accountList.add(account)
    }

    override fun updateAccount(account: Account) {
        removeAccount(account.ID)
        addAccount(account)
    }
    override fun removeAccount(id: Int) {
        accountList.removeIf { it.ID == id }
    }

    override fun getAllAccount() = accountList

    override fun getByIdAccount(id: Int): Account? {
        try {
            val result = accountList.filter { it.ID == id }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getByNameAccount(name: String): Account? {
        try {
            val result = accountList.filter { it.Name.trim() == name.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }
    //endregion

    //region Bank
    override fun addBank(bank: Bank) {
        bankList.add(bank)
    }

    override fun updateBank(bank: Bank) {
        removeBank(bank.ID)
        addBank(bank)
    }

    override fun removeBank(id: Int) {
        bankList.removeIf { it.ID == id }
    }

    override fun getAllBank() = bankList

    override fun getByIdBank(id: Int): Bank? {
        try {
            val result = bankList.filter { it.ID == id }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getByNameBank(name: String): Bank? {
        try {
            val result = bankList.filter { it.Name.trim() == name.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }
    //endregion

    //region Category
    override fun addCategory(category: Category) {
        categoryList.add(category)
    }

    override fun updateCategory(category: Category) {
        removeCategory(category.ID)
        addCategory(category)
    }

    override fun removeCategory(id: Int) {
        categoryList.removeIf { it.ID == id }
    }

    override fun getAllCategory() = categoryList

    override fun getByIdCategory(id: Int): Category? {
        try {
            val result = categoryList.filter { it.ID == id }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getByNameCategory(name: String): Category? {
        try {
            val result = categoryList.filter { it.Name.trim() == name.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }
    //endregion

    //region Currency
    override fun addCurrency(currency: Currency) {
        currencyList.add(currency)
    }

    override fun updateCurrency(currency: Currency) {
        removeCurrency(currency.ID)
        currencyList.add(currency)
    }

    override fun removeCurrency(id: Int) {
        currencyList.removeIf { it.ID == id }
    }

    override fun getAllCurrency() = currencyList

    override fun getByIdCurrency(id: Int): Currency? {
        try {
            val result = currencyList.filter { it.ID == id }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getByNameCurrency(name: String): Currency? {
        try {
            val result = currencyList.filter { it.Name.trim() == name.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getByCodeCurrency(code: String): Currency? {
        try {
            val result = currencyList.filter { it.Code.trim() == code.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }
    //endregion

    //region Tag
    override fun addTag(tag: Tag) {
        tagList.add(tag)
    }

    override fun updateTag(tag: Tag) {
        removeTag(tag.ID)
        addTag(tag)
    }

    override fun removeTag(id: Int) {
        tagList.removeIf { it.ID == id }
    }

    override fun getAllTag() = tagList

    override fun getByIdTag(id: Int): Tag? {
        try {
            val result = tagList.filter { it.ID == id }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getByNameTag(name: String): Tag? {
        try {
            val result = tagList.filter { it.Name == name.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }
    //endregion

    //region Transaction
    override fun addTransaction(transaction: Transaction) {
        transactionList.add(transaction)
    }

    override fun updateTransaction(transaction: Transaction) {
        removeTransaction(transaction.ID)
        addTransaction(transaction)
    }

    override fun removeTransaction(id: Int) {
        transactionList.removeIf { it.ID == id }
    }

    override fun getAllTransaction() = transactionList

    override fun getByIdTransaction(id: Int): Transaction? {
        try {
            val result = transactionList.filter { it.ID == id }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getByNameTransaction(description: String): Transaction? {
        try {
            val result = transactionList.filter { it.Description.trim() == description.trim() }
            return if (result.any()) result[0] else null
        } catch (e: Exception) {
            throw e
        }
    }
    //endregion
}