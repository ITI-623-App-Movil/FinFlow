package Entity

class Account {
    private var id: Int = 0
    private var name: String = ""
    private var balance: Double = 0.0
    private var currency: Currency = Currency()
    private var accountType: String = ""

    constructor()

    constructor(id: Int, name: String, balance: Double, currency: Currency, accountType: String) {
        this.id = id
        this.name = name
        this.balance = balance
        this.currency = currency
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
        get() = this.currency
        set(value) { this.currency = value }

    var AccountType: String
        get() = this.accountType
        set(value) { this.accountType = value }

    override fun toString(): String {
        return "${this.name} - ${this.currency.Name}"
    }
}