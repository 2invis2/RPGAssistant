package com.inviz.domain.entity.char_sheet

import com.inviz.domain.entity.Player
import com.inviz.domain.entity.RPGSystem


abstract class CharSheet(
    open val id: String,
    val system: RPGSystem,
    open val gameId: String?,
    open val player: Player,
    open val nameCharacter: String,
)