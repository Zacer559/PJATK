fun main(args: Array<String>) {
    val map = mapOf("Fries" to 1.23, "Kebab" to 5.87, "Ziemniaki" to 1.00)
    val filteredMap = map.map{it.value*0.8}
    println(filteredMap)
}