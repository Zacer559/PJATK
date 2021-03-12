val FORBIDDEN = 0
val EXECUTE = 1
val WRITE = 2
val READ = 4

fun main(args: Array<String>) {
println(checkPermissions(1))
}

fun checkPermissions(perms: Int): Boolean {
    return ((perms and WRITE )== WRITE) or ((perms and READ) == READ)
}