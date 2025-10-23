package Entity

class Tag {
    private var id: Int = 0
    private var name: String = ""

    constructor(id: Int, name: String) {
        this.id = id;
        this.name = name;
    }

    var ID: Int
        get() = this.id
        set(value) { this.id = value }

    var Name: String
        get() = this.name
        set(value) { this.name = value }
}