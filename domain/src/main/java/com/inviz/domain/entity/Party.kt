package com.inviz.domain.entity

import com.inviz.domain.entity.char_sheet.CharSheet

data class Party(
    val id: String,
    val name: String,
    val system: RPGSystem,
    val gameMaster: Player,
    val state: PartyState,
    val players: Set<Player>?,
    val charSheets: Set<CharSheet>?,
)