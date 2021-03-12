fun main(args: Array<String>) {
    val months = """January, February, March, April, May, June,  

               |July, August, September, October, November,  

       |December""".trimMargin()
    //manual convertion to list
    var res = listOf<String>()
    var buff = ""
    months.forEach { char ->
        if (char != ',') {
            if ((char != '\n') and (char != ' '))
                buff += char
        } else {
            res = res + buff
            buff = ""
        }
    }
    res = res + buff
    //a
    for (a in 1..5)
        print(res[a] + ",")
    //b
    res.forEach { month -> if (month.toCharArray()[0] == 'J') println(month) }
    //c
    res.forEachIndexed { index, month ->
        if ((index % 2) == 1) println(month)
    }
    //d
    var it = 0;
    while (it < 12) {
        println(res.get(it))
        it++;
    }
    //e
    e(res, 0)

    //f
    println(res.joinToString())
    //g
    res.forEach { month ->
        if (month.toCharArray()[0] == 'A') {
            var m = month.replace('A', '_')
            println(m)
        }
    }
}

//e
fun e(months: List<String>, index: Int) {

    if (months.size > index) {
        println(months.get(index))
        e(months, index + 1)
    }
    return


}