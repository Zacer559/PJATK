val FORBIDDEN = 0
val EXECUTE = 1
val WRITE = 2
val READ = 4

fun checkPermissions(perm: Int) = perm and (READ or EXECUTE) == (READ or EXECUTE)

class Permission() {
    var permissionValue: Int? = null;

    constructor(permission: Int?) : this() {
        this.permissionValue = permission;
    };

    operator fun inc(): Permission {
        val isWritable = (this.permissionValue?.and(WRITE))
        var res = permissionValue;
        if (isWritable != null) {
            res = if (isWritable == 0) permissionValue?.xor(WRITE) else permissionValue
        }
        return Permission(res);
    }

    operator fun dec(): Permission {
        val isWritable = (this.permissionValue?.and(WRITE))
        var res = permissionValue;
        if (isWritable != null) {
            res = if (isWritable > 0) permissionValue?.xor(WRITE) else permissionValue
        }
        return Permission(res);
    }

    operator fun plusAssign(p2: Permission) {
        this.permissionValue = (p2.permissionValue?.let { this.permissionValue?.or(it) })
    }

    operator fun minusAssign(p2: Permission) {
        this.permissionValue = (p2.permissionValue?.let { this.permissionValue?.xor(it) })
    }

}

fun main(args: Array<String>) {
    var p = Permission(2)
    var p2 = Permission(4)
    println(p.permissionValue)
    p++
    println(p.permissionValue)
    p--
    println(p.permissionValue)
    p--
    println(p.permissionValue)
    p += p2
    println(p.permissionValue)
    p -= p2
    println(p.permissionValue)
}