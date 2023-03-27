package com.inviz.domain.entity.char_sheet

import com.inviz.domain.entity.Player
import com.inviz.domain.entity.RPGSystem.*

//TODO надо разобрать систему и доделать модель чарника
data class CSCyberpunkRed(
    override val id: String,
    override val gameId: String?,
    override val player: Player,
    override val nameCharacter: String,
) : CharSheet(id, CYBERPUNK_RED, gameId, player, nameCharacter)
