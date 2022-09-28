package com.s16941.bombingclicker.activities

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.s16941.bombingclicker.R
import com.s16941.bombingclicker.mechanism.Game
import java.time.format.DateTimeFormatter

class RankingAdapter(private val games: List<Game>) : RecyclerView.Adapter<RankingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.ranking, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(this.games[position], position)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindItems(game: Game, position: Int) {
            val rankingView = itemView.findViewById(R.id.rankingName) as TextView
            val rankingTimeView = itemView.findViewById(R.id.rankingTime) as TextView
            val rankingPointsView = itemView.findViewById(R.id.rankingPoints) as TextView
            val rankingDateTimeView = itemView.findViewById(R.id.rankingDateTime) as TextView
            rankingView.text = "${position+1}: Earned ${game.getScore()} Game Points!"
            rankingTimeView.text = "Total time: ${game.getTotalTime()} seconds"
            rankingPointsView.text = "Earned: ${game.getTotalPoints()} points"
            rankingDateTimeView.text = game.getFinishedAt()!!.format(DateTimeFormatter.ISO_DATE_TIME)
        }
    }
}