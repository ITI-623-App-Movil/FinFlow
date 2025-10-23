package Entity

import java.util.Date

class Transaction {
    private var id: Int = 0
    private var amount: Double = 0.0
    private var description: String = ""
    private lateinit var date: Date
    private lateinit var category: Category
    private lateinit var account: Account
    private lateinit var tags: MutableList<Tag>

    constructor(id: Int, amount: Double, description: String, date: Date, category: Category,
        account: Account, tags: MutableList<Tag>) {
        this.id = id
        this.amount = amount
        this.description = description
        this.date = date
        this.category = category
        this.account = account
        this.tags = tags
    }

    var ID: Int
        get() = this.id
        set(value) { this.id = value }

    var Amount: Double
        get() = this.amount
        set(value) { this.amount = value }

    var Description: String
        get() = this.description
        set(value) { this.description = value }

    var Category: Category
        get() = this.category
        set(value) { this.category = value }

    var Account: Account
        get() = this.account
        set(value) { this.account = value }

    var Tags: MutableList<Tag>
        get() = this.tags
        set(value) { this.tags = value }
}