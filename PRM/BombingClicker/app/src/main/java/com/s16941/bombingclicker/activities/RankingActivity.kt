package com.s16941.bombingclicker.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.s16941.bombingclicker.R
import com.s16941.bombingclicker.mechanism.GameRepository
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RankingActivity : AppCompatActivity() , KodeinAware {
    override val kodein by kodein()
    private val gameRepository: GameRepository by instance()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        val upgradesView = findViewById<RecyclerView>(R.id.rankingView)
        upgradesView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        upgradesView.adapter = RankingAdapter(gameRepository.getRanking())
    }
}