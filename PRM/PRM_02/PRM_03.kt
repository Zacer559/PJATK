import java.util.*

fun main(args: Array<String>) {
    val original = "cat"
    var copy = original

    println(original == copy)
    println(original===copy)

    copy = Scanner(System.`in`).nextLine();
    println(original == copy)
    println(original===copy)

    // Yes there is a difference in equalities when input is cat
}
