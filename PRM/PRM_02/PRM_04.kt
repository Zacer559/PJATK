enum class Sex {
    MALE, FEMALE
}

class Person {
    var name: String
    var surname: String
    var sex: Sex

    constructor(name: String, surname: String, sex: Sex) {
        this.name = name
        this.surname = surname
        this.sex = sex
    }

    override fun toString(): String {
        return "${this.name};${this.surname};${this.sex}";
    }
}

fun String.toPerson(): Person {
    return Person(this.split(';')[0], this.split(';')[1], Sex.valueOf(this.split(';')[2]))
}

fun main(args: Array<String>) {
    var p1 = Person("alex", "kowalski", Sex.MALE)
    var p2 = Person("Gosia", "Powiatowska", Sex.FEMALE)
    val listOfPerson = listOf(p1, p2)

}