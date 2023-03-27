package com.inviz.domain.entity

data class Party(
    val id: String?,
    val name: String,
    val system: RPGSystem,
    val gameMaster: Player,
    val players: List<Player>,
)