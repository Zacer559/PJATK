package com.s16941.bombingclicker.mechanism

open class Upgrade(
    val cost: Int,
    val name: String,
    val type: String,
    val level: Int,
    val value: Int
) {
    fun isParent(upgrade: Upgrade): Boolean {
        if (this.level == (upgrade.level - 1)) {
            return true
        }
        return false
    }

    fun generateContext(): String {
        var msg = ""
        when (this.type) {
            TYPE_CLICK -> msg = "Will increase amount of clicked bombs by ${this.value}"
            TYPE_COST -> msg = "Will reduce cost of other upgrades to ${this.value}%"
            TYPE_TIME -> msg =
                "Will increase amount of automatically generated bombs by ${this.value} per second"
        }

        return msg
    }

    companion object {
        const val TYPE_COST: String = "cost"
        const val TYPE_CLICK: String = "bombs"
        const val TYPE_TIME: String = "time"
    }
}