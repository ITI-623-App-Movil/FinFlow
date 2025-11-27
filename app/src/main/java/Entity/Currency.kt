package Entity

class Currency {
    private var id: Int = 0
    private var code: String = ""
    private var name: String = ""

    constructor()

    constructor(id: Int, code: String, name: String) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    var ID: Int
        get() = this.id
        set(value) { this.id = value }

    var Code: String
        get() = this.code
        set(value) { this.code = value }

    var Name: String
        get() = this.name
        set(value) { this.name = value }
}