package com.inviz.domain.entity.char_sheet

import com.inviz.domain.entity.Player
import com.inviz.domain.entity.RPGSystem.*
import com.inviz.domain.entity.char_sheet.dnd.CharacterClass
import com.inviz.domain.entity.char_sheet.dnd.Parameters

//TODO надо доработать модель перслиста
data class CSDnD5e(
    override val id: String,
    override val gameId: String?,
    override val player: Player,
    override val nameCharacter: String,
    val characterClass: List<CharacterClass>,
    val experience: Int = 0,
    val parameters: Parameters,
    val proficiencyBonus: Byte,

) : CharSheet(id, DND_5E, gameId, player, nameCharacter)
