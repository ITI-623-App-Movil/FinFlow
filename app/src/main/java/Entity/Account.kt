package Entity

class Account {
    private var id: Int = 0
    private var name: String = ""
    private var balance: Double = 0.0
    private lateinit var currency: Currency
    private lateinit var bank: Bank
    private var accountType: String = ""

    constructor(id: Int, name: String, balance: Double, currency: Currency, bank: Bank,
                accountType: String) {
        this.id = id
        this.name = name
        this.balance = balance
        this.currency = currency
        this.bank = bank
        this.accountType = accountType
    }

    var ID: Int
        get() = this.id
        set(value) { this.id = value }

    var Name: String
        get() = this.name
        set(value) { this.name = value }

    var Balance: Double
        get() = this.balance
        set(value) { this.balance = value }

    var Currency: Currency
        get() = this.Currency
        set(value) { this.currency = value }

    var Bank: Bank
        get() = this.bank
        set(value) { this.Bank = value }

    var AccountType: String
        get() = this.accountType
        set(value) { this.accountType = value }
}