package com.s16941.bombingclicker.mechanism

interface GameStore {
    fun has(): Boolean
    fun store(game: Game)
    fun retrieve(): Game
    fun flush()
}