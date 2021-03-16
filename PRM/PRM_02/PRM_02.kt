fun main(args: Array<String>) {
    val a = "SomeString"
    val b: String? = null
    println(b?.length)
    println(a?.length)

    val l = b?.length ?: -1
    println(l);
}