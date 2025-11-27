package Entity

class Category {
    private var id: Int = 0
    private var type: String = "" // Income or Expense
    private var name: String = ""

    constructor()

    constructor(id: Int, type: String, name: String) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    var ID: Int
        get() = this.id
        set(value) { this.id = value }

    var Type: String
        get() = this.type
        set(value) { this.type = value }

    var Name: String
        get() = this.name
        set(value) { this.name = value }
}