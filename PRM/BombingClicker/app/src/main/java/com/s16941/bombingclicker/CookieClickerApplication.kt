package com.s16941.bombingclicker

import android.app.Application
import com.s16941.bombingclicker.storing.game.inMemoryGameRepositoryModule
import com.s16941.bombingclicker.storing.game.inMemoryGameStoreModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class CookieClickerApplication: Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(androidXModule(this@CookieClickerApplication))
        import(inMemoryGameRepositoryModule)
        import(inMemoryGameStoreModule)
    }

    override fun onCreate() {
        super.onCreate()
        val k = kodein
        println(k)
    }
}
