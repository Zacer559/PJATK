package com.s16941.bombingclicker.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.s16941.bombingclicker.R

import kotlinx.android.synthetic.main.game_activity.*
import kotlinx.android.synthetic.main.game_content.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.s16941.bombingclicker.storing.OnItemClickListener
import com.s16941.bombingclicker.storing.addOnItemClickListener
import com.s16941.bombingclicker.mechanism.*
import com.s16941.bombingclicker.mechanism.exceptions.InsufficientFundsException
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class GameActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val gameStore: GameStore by instance()
    public val gameRepository: GameRepository by instance()
    var isRunning = true
    val mainHandler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)
        registerUpgradeAdapter()

        try {
            mainHandler.post(object : Runnable {
                override fun run() {
                    if (isRunning) {
                        val pointsIncreased = gameStore.retrieve().increaseCounter()
                        button_cookie.animate().rotationBy(pointsIncreased.toFloat())
                            .setDuration(1 * 100).interpolator =
                            LinearInterpolator()
                        refreshPointsInView()
                        mainHandler.postDelayed(this, 1000)
                    }
                }
            })

        } catch (e: Exception) {
            Log.e("Error", e.message)
        }

        button_cookie.setOnClickListener { view ->
            val pointsIncreased = gameStore.retrieve().increasePoints()
            view.animate().rotationBy(pointsIncreased.toFloat()).setDuration(1 * 100).interpolator =
                LinearInterpolator()
            refreshPointsInView()
        }

        fab.setOnClickListener {
            changeUpgradesViewVisibility()
            refreshPointsInView()
        }
        fab2.setOnClickListener {
            if (gameStore.has()) {
                isRunning=false;
                mainHandler.removeCallbacksAndMessages(null);
                val game = gameStore.retrieve()
                game.finish()
                gameRepository.add(gameStore.retrieve())
                gameStore.flush()
                this.finishActivity(0)
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)

            }
        }
    }

    private fun changeUpgradesViewVisibility() {
        if (upgradeView.visibility == View.INVISIBLE)
            upgradeView.visibility = View.VISIBLE
        else
            upgradeView.visibility = View.INVISIBLE
    }

    private fun registerAdapter(upgradesView: RecyclerView): UpgradeAdapter {
        val adapter = UpgradeAdapter(UpgradeFactory.create(), gameStore.retrieve())
        upgradesView.adapter = adapter
        return adapter
    }

    @SuppressLint("SetTextI18n")
    private fun refreshPointsInView() {
        val game = gameStore.retrieve()
        yourPoints.text = game.getPoints().toString() + " bombs"
        pointsIncrementor.text = game.getPointsPerSecondMultiplier().toString() + " bombs/second"
    }

    @SuppressLint("WrongConstant")
    private fun registerUpgradeAdapter() {
        val upgradesView = findViewById<RecyclerView>(R.id.upgradeView)
        upgradesView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val adapter = registerAdapter(upgradesView)
        upgradesView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                try {
                    adapter.getUpgradeForType(position)?.let { gameStore.retrieve().buyUpgrade(it) }

                    Snackbar.make(view, "Upgrade Bought", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                    registerAdapter(upgradesView)
                } catch (e: InsufficientFundsException) {
                    Snackbar.make(view, "Insufficient funds", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        })
    }
}
