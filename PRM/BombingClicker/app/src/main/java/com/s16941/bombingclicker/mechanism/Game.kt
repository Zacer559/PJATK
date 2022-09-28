package com.s16941.bombingclicker.mechanism

import com.s16941.bombingclicker.mechanism.exceptions.InsufficientFundsException
import com.s16941.bombingclicker.mechanism.exceptions.InvalidUpgradeLevelException
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class Game {
    private val startedAt: LocalDateTime = LocalDateTime.now()
    private var finishedAt: LocalDateTime? = null
    private var totalPoints: Int = 0
    private var points: Int = 0
    private var clickMultiplier: Int = 1
    private var timeMultiplier: Int = 0
    private var costMultiplier: Double = 1.0

    private var boughtUpgrades: MutableMap<String, Upgrade> = mutableMapOf()

    public fun increasePoints(): Int {
        val points = 1 * this.clickMultiplier
        this.points += points
        this.totalPoints += points

        return points
    }

    public fun increaseCounter(): Int {
        val points = 1 * this.timeMultiplier
        this.points += points
        this.totalPoints += points

        return points
    }

    public fun getPoints(): Int {
        return this.points
    }

    public fun getPointsPerSecondMultiplier(): Int {
        return this.timeMultiplier
    }

    public fun buyUpgrade(upgrade: Upgrade) {
        guardAgainstInsufficientPoints(upgrade)
        guardAgainstWrongUpdateLevel(upgrade)

        this.points -= (upgrade.cost.toDouble() * this.costMultiplier).toInt()

        boughtUpgrades[upgrade.type] = upgrade

        when (upgrade.type) {
            Upgrade.TYPE_CLICK -> this.clickMultiplier += upgrade.value
            Upgrade.TYPE_TIME -> this.timeMultiplier += upgrade.value
            Upgrade.TYPE_COST -> this.costMultiplier = upgrade.value * 0.01
        }
    }

    public fun getBoughtUpgrades(): Map<String, Upgrade> {
        return this.boughtUpgrades.toMap()
    }

    private fun guardAgainstWrongUpdateLevel(upgrade: Upgrade) {
        if (upgrade.level > 1) {
            val currentUpgrade = this.boughtUpgrades[upgrade.type]
            if (currentUpgrade == null || !currentUpgrade.isParent(upgrade)) {
                throw InvalidUpgradeLevelException()
            }
        }
    }

    private fun guardAgainstInsufficientPoints(upgrade: Upgrade) {
        if (upgrade.cost * this.costMultiplier > this.points) {
            throw InsufficientFundsException()
        }
    }

    public fun getCostMultiplier(): Double {
        return this.costMultiplier
    }

    public override fun toString(): String {
        return "${this.startedAt} - ${this.points}"
    }


    fun finish() {
        this.finishedAt = LocalDateTime.now()
    }

    fun getScore(): Long {
        return (this.totalPoints - (this.getTotalTime()))
    }

    fun getTotalTime() = ChronoUnit.SECONDS.between(this.startedAt, this.finishedAt)
    fun getTotalPoints() = this.totalPoints
    fun getFinishedAt() = this.finishedAt
}