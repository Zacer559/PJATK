package com.s16941.bombingclicker.mechanism

interface GameRepository {
    fun getRanking(): List<Game>
    fun add(game: Game)
    fun findAll(): Collection<Game>
}