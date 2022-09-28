package com.s16941.bombingclicker.mechanism

class UpgradeFactory {
    companion object {
        fun create(): List<TypeUpgrades> {
            val upgrades: MutableList<TypeUpgrades> = mutableListOf()
            upgrades.add(
                0, TypeUpgrades(
                    Upgrade.TYPE_CLICK, listOf<Upgrade>(
                        Upgrade(30, "Work with 2 hands", Upgrade.TYPE_CLICK, 1, 1),
                        Upgrade(120, "Work with even 3 hands!", Upgrade.TYPE_CLICK, 2, 5),
                        Upgrade(1000, "Become octopus", Upgrade.TYPE_CLICK, 3, 10)
                    )
                )
            )
            upgrades.add(
                1, TypeUpgrades(
                    Upgrade.TYPE_COST, listOf<Upgrade>(
                        Upgrade(34, "Hire your mather", Upgrade.TYPE_COST, 1, 90),
                        Upgrade(130, "Hire your father", Upgrade.TYPE_COST, 2, 70),
                        Upgrade(6000, "Hire whole nation", Upgrade.TYPE_COST, 3, 50)
                    )
                )
            )
            upgrades.add(
                2, TypeUpgrades(
                    Upgrade.TYPE_TIME, listOf<Upgrade>(
                        Upgrade(20, "Build first automated bomb maker", Upgrade.TYPE_TIME, 1, 1),
                        Upgrade(90, "Improve the construction of bomb maker", Upgrade.TYPE_TIME, 2, 4),
                        Upgrade(4000, "Make MANY bomb makers", Upgrade.TYPE_TIME, 3, 30)
                    )
                )
            )
            return upgrades.toList()
        }
    }
}