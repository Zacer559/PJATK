package com.s16941.bombingclicker.activities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.s16941.bombingclicker.R
import com.s16941.bombingclicker.mechanism.Game
import com.s16941.bombingclicker.mechanism.TypeUpgrades
import com.s16941.bombingclicker.mechanism.Upgrade

class UpgradeAdapter(private val typeUpgrades: List<TypeUpgrades>, private val game: Game) :
    RecyclerView.Adapter<UpgradeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.upgrades, parent, false)
        return ViewHolder(v, this.game)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val upgradeToDisplay: Upgrade? = getUpgradeForType(position)

        if (upgradeToDisplay != null) {
            holder.bindItems(upgradeToDisplay)
        }

    }

    public fun getUpgradeForType(position: Int): Upgrade? {
        val boughtUpgrades = this.game.getBoughtUpgrades()
        val typeUpgrade = this.typeUpgrades.get(position)

        if (!boughtUpgrades.containsKey(typeUpgrade.type)) {
            return typeUpgrade.upgrades[0]
        }

        val lastBoughtUpgrade: Upgrade? = boughtUpgrades[typeUpgrade.type]
        if (lastBoughtUpgrade!!.level!=3)
            return typeUpgrade.upgrades[lastBoughtUpgrade!!.level]
        else
            return null
    }

    override fun getItemCount(): Int {
        return typeUpgrades.size
    }

    class ViewHolder(itemView: View, val game: Game) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindItems(upgrade: Upgrade) {
            val upgradeName = itemView.findViewById(R.id.upgradeName) as TextView
            val upgradeCost = itemView.findViewById(R.id.upgradeCost) as TextView
            val upgradeLevel = itemView.findViewById(R.id.upgradeLevel) as TextView
            val upgradeContext = itemView.findViewById(R.id.upgrade_context) as TextView
            upgradeName.text = upgrade.name
            upgradeLevel.text = "Level: ${upgrade.level}"
            upgradeCost.text = "Cost: ${(upgrade.cost * game.getCostMultiplier()).toInt()} points"
            upgradeContext.text = upgrade.generateContext()
        }
    }
}
