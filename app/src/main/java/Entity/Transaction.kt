package Entity

import androidx.compose.ui.text.intl.Locale
import java.time.LocalDate

class Transaction {
    private var id: Int = 0
    private var amount: Double = 0.0
    private var description: String = ""
    private lateinit var date: LocalDate
    private lateinit var category: Category
    private lateinit var account: Account
    private lateinit var tags: MutableList<Tag>

    constructor()

    constructor(id: Int, amount: Double, description: String, date: LocalDate, category: Category,
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

    var Date: LocalDate
        get() = this.date
        set(value) { this.date = value }

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